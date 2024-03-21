package it.unisannio.jmsRequestReply;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class RequestorImpl implements Requestor {

	private QueueSession qSession;
	private QueueConnection connection;
	private QueueSender sender;
	private QueueReceiver receiver;
	private Queue replyQueue;

	private boolean shared;
	private QueueSession q1Session;

	public RequestorImpl(String queueName, boolean sh) throws JMSException {
		shared = sh;
		String url = "tcp://localhost:61616";
		ActiveMQConnectionFactory connFactory = new ActiveMQConnectionFactory(url);
		connFactory.setTrustAllPackages(true);
		connection = connFactory.createQueueConnection();
		qSession = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		Queue destination = qSession.createQueue(queueName);
		sender = qSession.createSender(destination);

		q1Session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		replyQueue = q1Session.createTemporaryQueue();

		if (!shared) {
			receiver = q1Session.createReceiver(replyQueue);
		}
		connection.start();
	}

	public RequestorImpl(boolean sh) throws JMSException {
		this("RequestReply", sh);
		shared = sh;
	}

	@Override
	public Message request(Message msg) throws JMSException {
		QueueReceiver receiver = this.receiver;
		msg.setJMSReplyTo(replyQueue);
		sender.send(msg);

		if (shared) {
			String selector = "JMSCorrelationID= '" + msg.getJMSMessageID() + "'";
			receiver = q1Session.createReceiver(replyQueue, selector);
		}
		return receiver.receive();
	}

	@Override
	public TextMessage createTextMessage() throws JMSException {
		return qSession.createTextMessage();
	}

	@Override
	public ObjectMessage createObjectMessage() throws JMSException {
		return qSession.createObjectMessage();
	}

	@Override
	public Message createMessage() throws JMSException {
		return qSession.createMessage();
	}

	@Override
	public void close() throws JMSException {
		connection.close();
	}

}
