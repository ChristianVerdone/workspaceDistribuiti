package it.unisannio.jmsRequestReplyTest;

import javax.jms.JMSException;
import javax.jms.Message;

import it.unisannio.jmsRequestReply.Replier;
import it.unisannio.jmsRequestReply.ReplierImpl;

public class TestReplier {

	public static void main(String[] args) {
		try {
			Replier replier = new ReplierImpl("requestQueue", false) {

				public Message onRequest(Message msg) throws JMSException {
					Message rmsg = this.createMessage();
					rmsg.setIntProperty("fact", factOf(msg.getIntProperty("val")));
					System.out.println("Replier: message received");
					return rmsg;
				}
			};
			replier.start();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public static int factOf(int n) {
		return n < 2 ? 1 : n * factOf(n - 1);
	}
}
