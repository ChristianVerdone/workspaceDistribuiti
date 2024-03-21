package it.unisannnio.momChat;

import java.util.Scanner;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSChatClient {
	public static void main(String[] args) throws JMSException {
		String url = "tcp://localhost:61616";
		TopicConnectionFactory factory = new ActiveMQConnectionFactory(url);
		TopicConnection conection = factory.createTopicConnection();
		TopicSession session = conection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
		conection.start();
		
		Topic topic = session.createTopic("Music");
		
		TopicPublisher publisher = session.createPublisher(topic);
		TopicSubscriber subscriber = session.createSubscriber(topic);

		MessageListener listener = new MessageListener() {

			@Override
			public void onMessage(Message msg) {
				System.out.print("Peer: ");
				String strmsg = null;
				try {
					strmsg=((TextMessage)msg).getText();
					System.out.println(strmsg);
					
				}catch(JMSException e) {
					System.err.println(e);
				}
			}
		};
		subscriber.setMessageListener(listener);
		
		TextMessage msg = session.createTextMessage();
		Scanner sc = new Scanner(System.in);
		String line=null;
		do {
			System.out.print(" ");
			line=sc.nextLine();
			msg.setText(line);
			publisher.publish(msg);
		}while(!line.endsWith("."));
		
		session.close();
		conection.close();
	}
}
