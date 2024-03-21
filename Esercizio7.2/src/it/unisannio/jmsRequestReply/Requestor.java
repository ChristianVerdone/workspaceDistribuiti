package it.unisannio.jmsRequestReply;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

public class Requestor {
	public static void main(String[] args) throws Exception {
		String url = "tcp://localhost:61616";
		QueueConnectionFactory connFactory = new ActiveMQConnectionFactory(url);

		QueueConnection connection = connFactory.createQueueConnection();
		QueueSession qSession = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

		Queue queue = qSession.createQueue("RequestReply");
		QueueSender sender = qSession.createSender(queue);

		Queue tmpQueue = qSession.createTemporaryQueue();

		QueueReceiver receiver = qSession.createReceiver(tmpQueue);
		connection.start();

		TextMessage msg = qSession.createTextMessage();
		msg.setText("Hello jms world");
		msg.setJMSReplyTo(tmpQueue);
		sender.send(msg);

		TextMessage rmsg = (TextMessage) receiver.receive();
		System.out.println("initial msg: " + msg.getText());
		System.out.println("final msg: " + rmsg.getText());

		qSession.close();
		connection.close();

	}
}
