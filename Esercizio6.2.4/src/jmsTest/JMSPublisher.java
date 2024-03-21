package jmsTest;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSPublisher {
	public static void main (String[]args) {
		String uri = "tcp://localhost:61616";
		
		TopicConnectionFactory connFactory= new ActiveMQConnectionFactory(uri);
		try {
			TopicConnection connection = connFactory.createTopicConnection();
			TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Topic myQueue = session.createTopic("hello");
			
			TopicPublisher publisher= session.createPublisher(myQueue);
			Message myMsg= session.createTextMessage("Hello JMS World");
			publisher.publish(myMsg);
			
			session.close();
			connection.close();
		}catch(JMSException e) {
			System.err.println(e);
		}
	}
}
