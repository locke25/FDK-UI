package com.jcim.fdk.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jcim.fdk.dao.CustomerDao;
import com.jcim.fdk.dao.TestDao;
import com.jcim.fdk.service.MessageService;


@Configuration
public interface PersistenceConfig {
   
	public int getPort();
	
	@Bean DataSource dataSource();
	
	@Bean CustomerDao customerDao(); 
	
	@Bean TestDao testDao();
	
}
