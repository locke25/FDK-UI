package com.jcim.fdk.config;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JmsConfiguration {

	@Bean
	public String brokerUrl() {
		return "tcp://" + getIpAddress() + ":" + getPort();

	}

	private int getPort() {
		return StringUtils.isNotBlank(System.getProperty("portJms")) ? Integer.valueOf(System.getProperty("portJms")) : 61616;
	}

	private String getIpAddress() {
		try {
			return StringUtils.isNotBlank(System.getProperty("ipJms")) ? System.getProperty("ipJms") : InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			return "tcp://10.9.0.65:" + getPort();
		}
	}
}
