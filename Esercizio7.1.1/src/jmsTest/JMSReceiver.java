package jmsTest;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSReceiver {

	public static void main(String[] args) {
		String url = "tcp://localhost:61616";
		QueueConnectionFactory connFactory = new ActiveMQConnectionFactory(url);
		try {
			QueueConnection connection = connFactory.createQueueConnection();
			connection.start();
			QueueSession session = connection.createQueueSession(false, Session.CLIENT_ACKNOWLEDGE);
			Queue myQueue = session.createQueue("acks");
			
			QueueReceiver receiver = session.createReceiver(myQueue);

			for (int i = 0; 1 < 100; i++) {
				Message msg = receiver.receive();
				System.out.println(msg.getIntProperty("value"));
				msg.acknowledge();
			}
		} catch (JMSException e) {

		}
	}

}
