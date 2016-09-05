package com.jcim.fdk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.jcim.fdk.config.JmsConfiguration;
import com.jcim.fdk.config.PersistenceConfigImpl;
import com.jcim.fdk.config.RemotePersistenceConfig;

@Configuration
@Import({
	PersistenceConfigImpl.class,  // Persistence config and DAOs
	JmsConfiguration.class,		   // Config for JMS
	RemotePersistenceConfig.class // Remote access to DAOs via JMS
})
public class RootApplicationConfig 
{

}
