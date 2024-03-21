package it.unisannio.jmsRequestReply;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

public interface Requestor {
	
	public Message request(Message msg) throws JMSException;
	
	public TextMessage createTextMessage()  throws JMSException;
	
	public ObjectMessage createObjectMessage()  throws JMSException;
	
	public Message createMessage()  throws JMSException;
	
	public void close()  throws JMSException;
}
