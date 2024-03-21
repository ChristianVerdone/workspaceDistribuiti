package it.unisannio.jmsRequestReplyTest;

import javax.jms.*;

import it.unisannio.jmsRequestReply.Requestor;
import it.unisannio.jmsRequestReply.RequestorImpl;

public class TestRequestor {

	public static void main(String[] args) {
		try {
			Requestor requestor = new RequestorImpl("requestQueue", false);
			Message msg = requestor.createMessage();
			msg.setIntProperty("val", 5);
			Message rmsg = requestor.request(msg);
			System.out.println(rmsg.getIntProperty("fact"));

			requestor.close();

		} catch (JMSException e) {
			System.err.println(e);
		}
	}
}
