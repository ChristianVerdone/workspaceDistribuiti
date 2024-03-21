package it.unisannio.agrisensors;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

public class HTActuatorNode {
	private String uri, topic;

	public HTActuatorNode(String uri, String topic) {
		this.uri = uri;
		this.topic = topic;
	}

	public void run() {
		TopicConnectionFactory connFactory = new ActiveMQConnectionFactory(uri);
		try {
			TopicConnection connection = connFactory.createTopicConnection();
			TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

			Topic temperatureTopic = session.createTopic(topic);

			String selectorHT = "temperature >= 20";
			TopicSubscriber subscriber = session.createSubscriber(temperatureTopic, selectorHT,false);
			
			Actuator hta = new Actuator(new VirtualAction() {
				public String getActionName() {
					return "Irrigation";
				}
			});
			
			hta.start();

			subscriber.setMessageListener(new MessageListener() {

				@Override
				public void onMessage(Message msg) {
					try {
						System.out.println(msg.getDoubleProperty("temperature"));
						hta.actuate();
					} catch (JMSException e) {
						System.err.println(e);
					}
				}
			});
			connection.start();
		} catch (JMSException e) {
			System.err.println(e);
		}
	}
	public static void main(String []args) {
		String uri="tcp://localhost:61616";
		new HTActuatorNode(uri, "temperature").run();
	}
}
