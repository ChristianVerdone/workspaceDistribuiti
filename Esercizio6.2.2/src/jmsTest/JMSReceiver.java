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
			QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			Queue myQueue = session.createQueue("hello");
			QueueReceiver receiver = session.createReceiver(myQueue);
			receiver.setMessageListener(new MessageListener() {

				@Override
				public void onMessage(Message msg) {
					try {
						System.out.println(((TextMessage) msg).getText());
						session.close();
						connection.close();
					} catch (JMSException e) {
						System.err.println(e);
					}
				}
			});
		} catch (JMSException e) {
			System.err.println(e);
		}
	}
}
