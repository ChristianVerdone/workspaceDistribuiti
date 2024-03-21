package jmsTest;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSSubscriber {
	public static void main(String[] args) {
		String url = "tcp://localhost:61616";

		TopicConnectionFactory connFactory = new ActiveMQConnectionFactory(url);
		try {
			TopicConnection connection = connFactory.createTopicConnection();
			connection.start();

			TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic myTopic = session.createTopic("hello");

			TopicSubscriber subscriber = session.createSubscriber(myTopic);
			
			TextMessage msg = (TextMessage) subscriber.receive();
			System.out.println(msg.getText());

			session.close();
			connection.close();
			
		} catch (JMSException e) {
			System.err.println(e);
		}
	}
}
