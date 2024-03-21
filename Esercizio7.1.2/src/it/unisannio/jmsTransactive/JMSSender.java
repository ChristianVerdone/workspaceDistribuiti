package it.unisannio.jmsTransactive;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSSender {
	public static void main(String[] args) {
		String url = "tcp://localhost:61616";
		QueueConnectionFactory connFactory = new ActiveMQConnectionFactory(url);
		try {
			QueueConnection connection = connFactory.createQueueConnection();
			QueueSession session = connection.createQueueSession(true, Session.SESSION_TRANSACTED);
			Queue myQueue = session.createQueue("transactedQueue");
			QueueSender sender = session.createSender(myQueue);

			for (int i = 0; i < 10; i++) {
				Message msg = session.createMessage();
				msg.setIntProperty("value", i);
				sender.send(msg);

			}
			session.commit();
			session.close();
			connection.close();
		} catch (JMSException e) {
			System.err.println(e);
		}
	}
}
