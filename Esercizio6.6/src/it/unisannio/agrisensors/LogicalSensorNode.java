package it.unisannio.agrisensors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.jms.*;
import javax.swing.Timer;

import org.apache.activemq.ActiveMQConnectionFactory;

public class LogicalSensorNode {
	private double k, sum;
	private String uri, sensorTopicString, actuatorTopicString;
	private TopicPublisher publisher;
	private TopicConnectionFactory connFactory;
	private TopicConnection connection;
	private TopicSession session;
	private Topic sensorTopic, actuatorTopic;
	private TopicSubscriber subscriber;

	public LogicalSensorNode(String uri, String topicSensor, String topicActuator) {
		this.k = 0;
		this.sum = 0;
		this.uri = uri;
		this.sensorTopicString = topicSensor;
		this.actuatorTopicString = topicActuator;
	}

	public void run() {
		try {
			connFactory = new ActiveMQConnectionFactory(uri);
			connection = connFactory.createTopicConnection();
			connection.start();
			session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

			sensorTopic = session.createTopic(sensorTopicString);
			subscriber = session.createSubscriber(sensorTopic);

			actuatorTopic = session.createTopic(actuatorTopicString);
			publisher = session.createPublisher(actuatorTopic);

			subscriber.setMessageListener(new MessageListener() {
				@Override
				public void onMessage(Message msg) {
					try {
						sum += msg.getDoubleProperty("val");
						k++;

					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});

			Timer timer = new Timer(1000, new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						double media = calcolaMedia();
						sendMedia(media);
						reset();
					} catch (JMSException e1) {
						e1.printStackTrace();
					}
				}
			});
			timer.start();

		} catch (JMSException e) {
			System.err.println(e);
		}
	}

	public double calcolaMedia() {
		double media= sum / k;
		return media;
	}

	public void reset() {
		sum = 0;
		k = 0;
	}

	public void sendMedia(double media) throws JMSException {
		Message sampleMessage = session.createMessage();
		sampleMessage.setDoubleProperty(actuatorTopicString, media);
		System.out.println("media: "+media);
		publisher.publish(sampleMessage);
	}

	public static void main(String[] args) {
		String uri = "tcp://localhost:61616";
		String sensorTopic = "sensor";
		String actuatorTopic = "temperature";
		
		new LogicalSensorNode(uri, sensorTopic, actuatorTopic).run(); 
	}

}
