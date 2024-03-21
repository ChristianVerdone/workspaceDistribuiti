package it.unisannio.agrisensors;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

public class SensorNode {
	private static class Sensor extends Thread {
		private String uri, topic;
		private double[] samples;
		private int sensorId;
		private int interval;

		public Sensor(String uri, String topic, double[] samples, int sensorId, int interval) {
			this.uri = uri;
			this.topic = topic;
			this.samples = samples;
			this.sensorId = sensorId;
			this.interval = interval;
		}

		public void run() {
			int i = 0;
			try {
				TopicConnectionFactory connFactory = new ActiveMQConnectionFactory();
				TopicConnection connection = connFactory.createTopicConnection();
				TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
				Topic topic1 = session.createTopic(topic);
				TopicPublisher publisher = session.createPublisher(topic1);

				Message sampleMsg = session.createMessage();
				while (true) {
					sampleMsg.setDoubleProperty("val", samples[i++ % samples.length]);
					sampleMsg.setIntProperty("sensorId", sensorId);
					publisher.publish(sampleMsg);
					System.out.println("Sensor " + sensorId + ": " + sampleMsg.getDoubleProperty("val"));
					Thread.sleep(interval);
				}
			} catch (JMSException | InterruptedException e) {
				System.err.println(e);
			}
		}
	}

	public static void main(String[] args) {
		String uri = "tcp://localhost:61616";
		double[] samples = new double[] { 10, 10, 8, 8, 24, 4, 3, 2, 1, 2, 6, 23 };
		double[] samples1 = new double[] { 26, 12, 5, 8, 24, 1, 2, 6, 23, 4, 3, 2 };
		double[] samples2 = new double[] { 11, 7, 24, 3, 18, 22, 21, 1, 5, 7, 3, 2, 31, 19, 25, 31, 2, 5, 4, 27, 15 };
		double[] samples3 = new double[] { 10, 8, 24, 3, 5, 8, 15, 21, 15, 10, 5, 3, 6 };

		new Sensor(uri, "sensor", samples, 0, 1000).start();
		new Sensor(uri, "sensor", samples1, 1, 500).start();
		new Sensor(uri, "sensor", samples2, 2, 500).start();
		new Sensor(uri, "sensor", samples3, 3, 500).start();
	}
}
