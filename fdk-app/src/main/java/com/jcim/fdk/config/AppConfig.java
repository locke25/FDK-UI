package com.jcim.fdk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.jcim.fdk.WfsView;
import com.jcim.fdk.WfsViewImpl;
import com.vaadin.ui.UI;

@Configuration
@Import(MVPFrameworkConfig.class)
public class AppConfig
{	
	private @Autowired
	MVPFrameworkConfig mvpFrameworkConfig;
	
	@Autowired
	private UI ui;
	
	@Bean
	public WfsView wfsView()
	{
		return new WfsViewImpl(mvpFrameworkConfig.mainWindow());
	}	
	
}