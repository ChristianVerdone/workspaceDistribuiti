package jmsTest;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSSubscriber {

	public static void main(String[] args) {
		String url = "tcp://localhost:61616";
		TopicConnectionFactory connFactory = new ActiveMQConnectionFactory(url);
		try {
			TopicConnection connection = connFactory.createTopicConnection();
			connection.setClientID("client1");
			connection.start();
			TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

			Topic myTopic = session.createTopic("hello");
			TopicSubscriber subscriber = session.createDurableSubscriber(myTopic, "sub1");
			subscriber.setMessageListener(new MessageListener() {

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
