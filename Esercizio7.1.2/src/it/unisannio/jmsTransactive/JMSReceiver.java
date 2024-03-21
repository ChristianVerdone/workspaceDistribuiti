package it.unisannio.jmsTransactive;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSReceiver {
	public static void main(String[] args) {
		String url = "tcp://localhost:61616";
		QueueConnectionFactory connFactory = new ActiveMQConnectionFactory(url);

		try {
			QueueConnection connection = connFactory.createQueueConnection();
			connection.start();
			QueueSession session = connection.createQueueSession(true, Session.SESSION_TRANSACTED);

			Queue myQueue = session.createQueue("transactedQueue");
			
			QueueReceiver receiver = session.createReceiver(myQueue);
			
			for (int i = 0; i < 10; i++) {
				Message msg = receiver.receive();
				System.out.println(msg.getIntProperty("value"));
				msg.acknowledge();
			}
			
			//emulazione di un problema sulla comunicazione
			session.rollback();
			
			System.out.println("retry");
			
			for (int i = 0; i < 10; i++) {
				Message msg = receiver.receive();
				System.out.println(msg.getIntProperty("value"));
				msg.acknowledge();
			}
			
			//chiusura con successo della comunicazione
			session.commit();
			session.close();
			connection.close();

		} catch (JMSException e) {
			System.err.println("Error " + e);
		}
	
	}

}
