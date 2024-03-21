package it.unisannio.agrisensors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.jms.*;
import javax.swing.Timer;

import org.apache.activemq.ActiveMQConnectionFactory;

public class LogicalSensorNodeSmart {
	private String uri, sensorTopicString, actuatorTopicString;
	private TopicPublisher publisher;
	private TopicConnectionFactory connFactory;
	private TopicConnection connection;
	private TopicSession session;
	private Topic sensorTopic, actuatorTopic;
	private TopicSubscriber subscriber;
	private HashMap<Integer, ArrayList<Double>> misureSensori;

	public LogicalSensorNodeSmart(String uri, String topicSensor, String topicActuator) {
		this.uri = uri;
		this.sensorTopicString = topicSensor;
		this.actuatorTopicString = topicActuator;
		this.misureSensori = new HashMap<Integer, ArrayList<Double>>();
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
						int sensorId = msg.getIntProperty("sensorId");
						ArrayList<Double> misure = misureSensori.get(sensorId);

						if (misure == null) {
							misure = new ArrayList<Double>();
							misureSensori.put(sensorId, misure);
						}
						misure.add(msg.getDoubleProperty("val"));
					} catch (JMSException e) {
						e.printStackTrace();
					}
				}
			});

			Timer timer = new Timer(1000, new ActionListener() {

				@Override
				public synchronized void actionPerformed(ActionEvent e) {
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

	public synchronized double calcolaMediaSensore(int sensorId) {
		ArrayList<Double> misure = misureSensori.get(sensorId);
		double sum = 0;
		for (Double d : misure) {
			sum += d;
		}
		return sum / misure.size();
	}

	public synchronized double calcolaMedia() {
		double sum = 0;
		for (Integer i : misureSensori.keySet()) {
			sum += calcolaMediaSensore(i);
		}
		return sum / misureSensori.size();
	}

	public synchronized void reset() {
		misureSensori = new HashMap<Integer, ArrayList<Double>>();
	}

	public synchronized void sendMedia(double media) throws JMSException {
		Message sampleMessage = session.createMessage();
		sampleMessage.setDoubleProperty(actuatorTopicString, media);
		System.out.println("media: " + media);
		publisher.publish(sampleMessage);
	}

	public static void main(String[] args) {
		String uri = "tcp://localhost:61616";
		String sensorTopic = "sensor";
		String actuatorTopic = "temperature";

		new LogicalSensorNode(uri, sensorTopic, actuatorTopic).run();
	}

}
