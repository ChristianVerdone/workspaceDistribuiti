package it.unisannio.momChat;

import java.util.Scanner;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSChatClient {

	public static void main(String[] args) throws JMSException {
		String url = "tcp://localhost:61616";
		TopicConnectionFactory connFactory = new ActiveMQConnectionFactory(url);
		TopicConnection connection = connFactory.createTopicConnection();
		TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

		connection.start();

		Topic topic = session.createTopic("Music");
		TopicPublisher publisher = session.createPublisher(topic);

		String selector = "Subtopic = 'Jazz'";

		TopicSubscriber subscriber = session.createSubscriber(topic, selector, true);

		MessageListener listener = new MessageListener() {

			@Override
			public void onMessage(Message msg) {
				System.out.print("Peer: ");
				String stringMsg = null;
				try {
					stringMsg = ((TextMessage) msg).getText();
					System.out.println(stringMsg);
				} catch (JMSException e) {
					e.printStackTrace();
				}
			}
		};

		subscriber.setMessageListener(listener);

		TextMessage msg = session.createTextMessage();
		Scanner sc = new Scanner(System.in);
		String line = null;
		do {
			System.out.print(" ");
			line = sc.nextLine();
			msg.setStringProperty("Subtopic", "Jazz");
			msg.setText(line);
			publisher.publish(msg);
		} while (!line.endsWith("."));

		session.close();
		connection.close();
		sc.close();
	}
}
