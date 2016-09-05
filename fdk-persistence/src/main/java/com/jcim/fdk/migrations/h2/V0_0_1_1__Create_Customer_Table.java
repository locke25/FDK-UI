package com.jcim.fdk.migrations.h2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.googlecode.flyway.core.api.migration.spring.SpringJdbcMigration;

public class V0_0_1_1__Create_Customer_Table implements SpringJdbcMigration 
{

	private final static Logger log = LoggerFactory.getLogger(V0_0_1_1__Create_Customer_Table.class);
	
	@Override
	public void migrate(JdbcTemplate jdbc) throws Exception {
		log.debug("creating table 'ACCOUNT'");
		jdbc.update("CREATE TABLE ACCOUNT (" +
				"id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
				"creationDate timestamp," +
				"lastUpdated timestamp," +
				"name varchar(255)," + 
				"industry_type varchar(255)" +
				")");
	}

}
