package it.unisannio.replicatedObject;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;

import it.unisannio.jmsRequestReply.ReplierImpl;

public class ReplicatedObject<T> extends ReplierImpl {
	private T impl;

	public ReplicatedObject(T o, String des, boolean shared) throws JMSException {
		super(des, shared);
		impl = o;
	}

	public ReplicatedObject(T o, String des) throws JMSException {
		this(o, des, false);
	}

	@Override
	public Message onRequest(Message msg) throws JMSException {
		// TODO Auto-generated method stub
		Call c = (Call) ((ObjectMessage) msg).getObject();
		ObjectMessage rmsg = createObjectMessage();

		try {
			Method method = impl.getClass().getMethod(c.methodName, c.argTypes);
			Object r = method.invoke(impl, c.arguments);
			rmsg.setObject((Serializable) r);

		} catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
			System.err.println(e);
		}
		return rmsg;
	}
}
