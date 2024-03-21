package it.unisannio.agrisensors;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

public class LTActuatorNode {
	private String uri, topic;

	public LTActuatorNode(String uri, String topic) {
		this.uri = uri;
		this.topic = topic;
	}

	public void run() {
		TopicConnectionFactory connFactory = new ActiveMQConnectionFactory(uri);
		try {
			TopicConnection connection = connFactory.createTopicConnection();
			TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

			Topic temperatureTopic = session.createTopic(topic);

			String selectorLT = "temperature <= 4";
			TopicSubscriber subscriber = session.createSubscriber(temperatureTopic, selectorLT, false);

			Actuator lta = new Actuator(new VirtualAction() {
				public String getActionName() {
					return "Anti-frost procedures";
				}
			});
			lta.start();

			subscriber.setMessageListener(new MessageListener() {

				@Override
				public void onMessage(Message msg) {
					try {
						System.out.println(msg.getDoubleProperty("temperature"));
						lta.actuate();
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

	public static void main(String[] args) {
		String uri = "tcp://localhost:61616";
		new LTActuatorNode(uri, "temperature").run();
	}
}