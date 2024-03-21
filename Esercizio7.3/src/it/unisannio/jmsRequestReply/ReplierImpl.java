package it.unisannio.jmsRequestReply;

import javax.jms.InvalidDestinationException;
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
import org.apache.activemq.DestinationDoesNotExistException;

public abstract class ReplierImpl implements Replier {
	private QueueConnection connection;
	private QueueReceiver receiver;

	private QueueSession qSession;
	private QueueSession q1Session;
//private Queue queue;
	private boolean shared;

	public ReplierImpl(String queueName, boolean sh) throws JMSException {
		shared = sh;
		String url = "tcp://localhost:61616";
		ActiveMQConnectionFactory connFactory = new ActiveMQConnectionFactory(url);
		connFactory.setTrustAllPackages(true);
		connection = connFactory.createQueueConnection();
		qSession = connection.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE);

		Queue queue = qSession.createQueue(queueName);
		receiver = qSession.createReceiver(queue);

		q1Session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
	}

	@Override
	public TextMessage createTextMessage() throws JMSException {
		return qSession.createTextMessage();
	}

	@Override
	public abstract Message onRequest(Message msg) throws JMSException;

	@Override
	public ObjectMessage createObjectMessage() throws JMSException {
		return qSession.createObjectMessage();
	}

	@Override
	public Message createMessage() throws JMSException {

		return q1Session.createMessage();
	}

	@Override
	public void start() throws JMSException {

		connection.start();
		Message msg = null;
		while (true) {
			msg = receiver.receive();
			try {
				Queue tmpQueue = (Queue) msg.getJMSReplyTo();
				Message rmsg = onRequest(msg);

				QueueSender sender = q1Session.createSender(tmpQueue);
				if (shared) {
					rmsg.setJMSCorrelationID(msg.getJMSMessageID());
				}
				sender.send(rmsg);
				
				msg.acknowledge();
				
			} catch (InvalidDestinationException e) {
				System.err.println("Can not send the message to an unavailable");
			} catch (DestinationDoesNotExistException e) {
				System.err.println("Temporary destinatione not available");
			}
		}
	}

	@Override
	public void close() throws JMSException {
		connection.close();
	}
}
