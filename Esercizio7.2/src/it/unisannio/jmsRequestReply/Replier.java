package it.unisannio.jmsRequestReply;

import javax.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class Replier {
	public static void main(String[] args) throws Exception {
		String url = "tcp://localhost:61616";
		QueueConnectionFactory factory = new ActiveMQConnectionFactory(url);

		QueueConnection connection = factory.createQueueConnection();
		QueueSession qSession = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

		Queue queue = qSession.createQueue("RequestReply");
		QueueReceiver receiver = qSession.createReceiver(queue);
		connection.start();

		TextMessage msg = (TextMessage) receiver.receive();
		Queue tmpQueue = (Queue) msg.getJMSReplyTo();

		QueueSession q1Session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		QueueSender sender = q1Session.createSender(tmpQueue);
		TextMessage rmsg = q1Session.createTextMessage(msg.getText() + " replied ");

		sender.send(rmsg);
	}
}
