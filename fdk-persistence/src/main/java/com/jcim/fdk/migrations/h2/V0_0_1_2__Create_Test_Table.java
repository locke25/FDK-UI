package com.jcim.fdk.migrations.h2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.googlecode.flyway.core.api.migration.spring.SpringJdbcMigration;

public class V0_0_1_2__Create_Test_Table implements SpringJdbcMigration 
{
	private final static Logger log = LoggerFactory.getLogger(V0_0_1_2__Create_Test_Table.class);
	
	@Override
	public void migrate(JdbcTemplate jdbc) throws Exception {
		log.debug("creating table 'Test' krawennnnzzzzzz mannnnnnnnnn\n\n\n");
		jdbc.update("CREATE TABLE Test (" +
				"id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
				"creationDate timestamp," +
				"lastUpdated timestamp," +
				"testString varchar(255)," + 
				"testBoolean tinyint," +
				"testEnum tinyint" +
				")");
		
		jdbc.update("INSERT INTO Test (id,creationDate,lastUpdated, testString, testBoolean, testEnum) VALUES (100,{ts '2012-11-16 17:59:15.'},{ts '2012-11-19 12:43:47.'},'testBLub',1, 0)");
		
		
	}

}
