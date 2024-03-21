package it.unisannio.agrisensors;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

public class SensorNode {
	private static class Sensor {
		private String uri, topic;
		private double[] samples;

		public Sensor(String uri, String topic, double[] samples) {
			this.uri = uri;
			this.topic = topic;
			this.samples = samples;
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
					sampleMsg.setDoubleProperty(topic, samples[i++ % samples.length]);
					publisher.publish(sampleMsg);
					System.out.println("Sensor: " + sampleMsg.getDoubleProperty(topic));
					Thread.sleep(1000);
				}
			} catch (JMSException | InterruptedException e) {
				System.err.println(e);
			}
		}
	}

	public static void main(String[] args) {
		String uri = "tcp://localhost:61616";
		double[] samples = new double[] { 10, 10, 8, 8,24, 4, 3, 2, 1, 2, 6, 23 };
		new Sensor(uri, "temperature", samples).run();
	}
}
