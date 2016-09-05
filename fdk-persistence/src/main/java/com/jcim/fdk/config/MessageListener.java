package com.jcim.fdk.config;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQDestination;
import org.springframework.jms.remoting.JmsInvokerServiceExporter;
import org.springframework.remoting.support.RemoteInvocationResult;

public class MessageListener extends JmsInvokerServiceExporter {

	public static final String DEFAULT_REPLY_QUEUE = "queue://replyQueue";
	
	@Override
	public void onMessage(Message requestMessage, Session session)
			throws JMSException {
		
//		if(requestMessage instanceof ActiveMQBytesMessage) {
//			System.out.println(new String(((ActiveMQBytesMessage)requestMessage).getContent().getData()));
//		}
		
		if(requestMessage.getStringProperty("destination") != null) {
			requestMessage.setJMSReplyTo(ActiveMQDestination.createDestination(requestMessage.getStringProperty("destination"), ActiveMQDestination.QUEUE_TYPE));
		} else if (requestMessage.getJMSReplyTo() == null) {
			requestMessage.setJMSReplyTo(ActiveMQDestination.createDestination(DEFAULT_REPLY_QUEUE, ActiveMQDestination.QUEUE_TYPE));
		}
		super.onMessage(requestMessage, session);
	}

	@Override
	protected Message createResponseMessage(Message request, Session session, RemoteInvocationResult result) throws JMSException {
		Message response = super.createResponseMessage(request, session, result);
		if(request.propertyExists("requestId")) {
			response.setStringProperty("requestId", request.getStringProperty("requestId"));
		}
		return response;
	}
}
