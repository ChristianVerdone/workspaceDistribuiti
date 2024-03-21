package jmsTest;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSSender {
	public static void main(String[] args) {
		String url = "tcp://localhost:61616";
		QueueConnectionFactory connFactory = new ActiveMQConnectionFactory(url);
		try {
			QueueConnection connection = connFactory.createQueueConnection();
			QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Queue myQueue = session.createQueue("hello");
			QueueSender sender = session.createSender(myQueue);
			Message myMsg = session.createTextMessage("Hello Jms world");
			
			sender.send(myMsg);
			session.close();
			connection.close();
		} catch (JMSException e) {
			System.err.println(e);
		}
	}
}
