package com.jcim.fdk.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.command.ActiveMQDestination;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.remoting.JmsInvokerProxyFactoryBean;
import org.springframework.jms.remoting.JmsInvokerServiceExporter;
import org.springframework.jms.support.converter.MarshallingMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;

import com.jcim.fdk.dao.CustomerDao;
import com.jcim.fdk.dao.TestDao;
import com.jcim.fdk.service.MessageService;

@Configuration
public class RemotePersistenceConfig {

    @Autowired
    private JmsConfiguration jmsConfiguration;

    @Autowired
    private PersistenceConfig persistenceConfig;


    // -------------- JMS Export
    @Bean
    public BrokerService brokerService() throws Exception {
        BrokerService broker = new BrokerService();
        broker.setUseJmx(false);
        // configure the broker
        broker.addConnector(jmsConfiguration.brokerUrl());

        broker.setDestinations(new ActiveMQDestination[] {
                ActiveMQDestination.createDestination(MessageListener.DEFAULT_REPLY_QUEUE,
                        ActiveMQDestination.QUEUE_TYPE),
                ActiveMQDestination.createDestination(ActiveMQDestination.TOPIC_QUALIFIED_PREFIX,
                        ActiveMQDestination.TOPIC_TYPE) });

        broker.start();

        return broker;
    }

    @DependsOn("brokerService")
    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        final ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(jmsConfiguration.brokerUrl());
        return connectionFactory;
    }

    @Bean
    public JmsInvokerServiceExporter customerDaoJmsExporter() {
        return getJmsExporter(CustomerDao.class, persistenceConfig.customerDao());
    }

    @Bean
    public SimpleMessageListenerContainer customerDaoMessages() {
        return messages("customerDaoQueue", customerDaoJmsExporter());
    }
      
    @Bean
    public JmsInvokerServiceExporter testDaoJmsExporter() {
        return getJmsExporter(TestDao.class, persistenceConfig.testDao());
    }

    @Bean
    public SimpleMessageListenerContainer testDaoMessages() {
        return messages("testDaoQueue", testDaoJmsExporter());
    }
    
    
    private <T>JmsInvokerServiceExporter getJmsExporter(Class<T> clazz, T dao) {
        final JmsInvokerServiceExporter invokerServiceExporter = new MessageListener();
        invokerServiceExporter.setServiceInterface(clazz);
        invokerServiceExporter.setService(dao);

        final XStreamMarshaller marshaller = new XStreamMarshaller();

        final MarshallingMessageConverter converter = new MarshallingMessageConverter();
        converter.setMarshaller(marshaller);
        converter.setUnmarshaller(marshaller);
        converter.afterPropertiesSet();

        invokerServiceExporter.setMessageConverter(converter);
        invokerServiceExporter.afterPropertiesSet();
        return invokerServiceExporter;
    }

    private SimpleMessageListenerContainer messages(String queueName, JmsInvokerServiceExporter jmsExporter) {
        final SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setDestination(new ActiveMQQueue(queueName));
        container.setConcurrentConsumers(25);
        container.setMessageListener(jmsExporter);
        container.afterPropertiesSet();
        return container;
    }
    
    	
	@Bean
	public MessageService messageService() {
		final JmsInvokerProxyFactoryBean proxyFactoryBean = new JmsInvokerProxyFactoryBean();
		proxyFactoryBean.setServiceInterface(MessageService.class);
		proxyFactoryBean.setConnectionFactory(connectionFactory());
		proxyFactoryBean.setQueue(new ActiveMQQueue("messageServiceQueue"));
		proxyFactoryBean.setReceiveTimeout(300000);

		final XStreamMarshaller marshaller = new XStreamMarshaller();

		final MarshallingMessageConverter converter = new MarshallingMessageConverter();
		converter.setMarshaller(marshaller);
		converter.setUnmarshaller(marshaller);
		converter.afterPropertiesSet();

		proxyFactoryBean.setMessageConverter(converter);

		proxyFactoryBean.afterPropertiesSet();

		return (MessageService) proxyFactoryBean.getObject();

	}
}
