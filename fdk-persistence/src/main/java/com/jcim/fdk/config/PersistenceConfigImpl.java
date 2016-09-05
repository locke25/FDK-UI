package com.jcim.fdk.config;

import java.beans.PropertyVetoException;
import java.io.File;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.googlecode.flyway.core.Flyway;
import com.jcim.fdk.dao.CustomerDao;
import com.jcim.fdk.dao.TestDao;
import com.jcim.fdk.dao.impl.CustomerDaoImpl;
import com.jcim.fdk.dao.impl.TestDaoImpl;
import com.jcim.fdk.model.Customer;
import com.jcim.fdk.model.Test;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableTransactionManagement
public class PersistenceConfigImpl implements PersistenceConfig {

    private static final Logger log = LoggerFactory.getLogger(PersistenceConfigImpl.class);

    protected static final String H2_DRIVER = "org.h2.Driver";
    
    @Autowired
    private ApplicationContext context;
    
    public PersistenceConfigImpl() {
    	
    }
    
     @Bean
     public DataSource dataSource() {
    
//    	 JndiObjectFactoryBean jndi = new JndiObjectFactoryBean();
//    	 jndi.setExpectedType(DataSource.class);
//    	 jndi.setJndiName("jndi/ds/taskmanager");
//    	 DataSource ds = (DataSource) jndi.getObject();
    
    	 DataSource ds = null;
    	 
    	 if(ds == null /*&& VersionUtils.isSnapshotVersion(persistenceVersion()) */) {
    		 
//    		 log.debug("no container datasource found. Initializing a new one since we are currently in development mode [{}]",
//		     persistenceVersion());
		     
    		 final File dbDir = new File(System.getProperty("java.io.tmpdir"), "taskmanager");
		     dbDir.mkdirs();
		     
		     final String dbPath = dbDir.getAbsolutePath() + File.separator + "db";
		     log.debug("using h2 database at {}", dbPath);
		     
		     final ComboPooledDataSource cpds = new ComboPooledDataSource();
		     try {
		    	 cpds.setDriverClass("org.h2.Driver"); //loads the jdbc driver
		     } catch (PropertyVetoException e) {
		    	 throw new RuntimeException("could not load jdbc driver",e);
		     }
		     
		     cpds.setJdbcUrl( "jdbc:h2:file:"+dbPath );
		     cpds.setUser("sa");		    
		    
		     return cpds;
    	 }
    	 
    	 return ds;
     }

    /**
     * Evaluates the dbDriver argument to determine, which database config to load.
     * 
     * @return "h2" or "oracle"
     */
	private String getDbDriverPrefix() 
	{
//		String dbDriverPrefix = System.getProperty("dbDriver");
//		if(dbDriverPrefix == null || 
//		   dbDriverPrefix.equals("") || 
//		   ! dbDriverPrefix.equals("oracle"))
//		{
//			dbDriverPrefix = "h2";
//		}
//		return dbDriverPrefix;
		return "h2";
	}
            

    public @Bean Flyway flyway() {
        Flyway flyway = new Flyway();
        DataSource dataSource = dataSource();
        
        flyway.setDataSource(dataSource);
        flyway.setLocations(getFlywayLocations(dataSource));
        
        if (flyway.status() == null) {
            log.info("initializing flyway ...");
            flyway.init();
        }

        log.debug("flyway version before migration {}", flyway.status().getVersion());
        final int migrations = flyway.migrate();
        if (migrations > 0) {
            log.debug("flyway version is {} after applying {} migrations", flyway.status().getVersion(), migrations);
        } else {
            log.debug("your database is already up to date");
        }
        return flyway;
    }

    protected String[] getFlywayLocations(DataSource dataSource) {
        
    	if(dataSource instanceof ComboPooledDataSource)
    	{
    		ComboPooledDataSource cpds =(ComboPooledDataSource) dataSource;
    		
    		String driverName = cpds.getDriverClass();
    		
    		if(driverName.equals(H2_DRIVER))
    		{
    			log.info("Using Location " + "com.jcim.fdk.migrations.h2" + " as migration source!");
    			return new String[] { "com.jcim.fdk.migrations.h2" };
    		}
    	}
    	
    	log.info("Using Location " + "com.jcim.fdk.migrations.oracle" + " as migration source!");
    	return new String[] { "com.jcim.fdk.migrations.oracle" };
    }
    
    /**
     * Hibernate SessionFactory
     */
    @DependsOn("flyway")
    public @Bean
    SessionFactory sessionFactory() {
    	
    	Properties p = new Properties();
    	InputStream dbConfigStream = 
    			this.getClass().getClassLoader().getResourceAsStream("db.properties");
    	try 
    	{
    		p.load(dbConfigStream);
    		
    		String dbDriverPrefix = getDbDriverPrefix();
    		
    		return new LocalSessionFactoryBuilder(dataSource())
            .addAnnotatedClasses(
            		Customer.class,         		
            		Test.class
            		)
            .setProperty("hibernate.search.default.directory_provider", "filesystem")
            .setProperty("hibernate.dialect", p.getProperty(dbDriverPrefix + ".hibernate.dialect"))
            .setProperty("hibernate.show_sql", "true")
            .buildSessionFactory();
   
		} 
    	catch (Exception e) 
		{
			throw new RuntimeException("Could not create session factory!", e);
		}
    }

    @Override
    public @Bean
    CustomerDao customerDao() {
        final CustomerDaoImpl customerDao = new CustomerDaoImpl(context);
        customerDao.setSessionFactory(sessionFactory());
        return customerDao;
    }    
   
    @Override
    public @Bean
    TestDao testDao() {
        final TestDaoImpl testDao = new TestDaoImpl(context);
        testDao.setSessionFactory(sessionFactory());
        return testDao;
    }
    
    public @Bean
    HibernateTransactionManager txManager() {
        return new HibernateTransactionManager(sessionFactory());
    }


    public int getPort() {
        int port = StringUtils.isNotBlank(System.getProperty("portClient")) ? Integer.valueOf(System
                .getProperty("portClient")) : 1099;
        return port;
    }


	
}

