package it.unisannio.replicatedObject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;

import it.unisannio.jmsRequestReply.Requestor;
import it.unisannio.jmsRequestReply.RequestorImpl;

public class ReplicatedObjectFactory<T> {
	private Class<T> type;
	private boolean shared;

	public ReplicatedObjectFactory(Class<T> c, boolean shared) {
		type = c;
		this.shared = shared;
	}

	public ReplicatedObjectFactory(Class<T> c) {
		this(c,false);
	}
	
	public T create(String dest) {
		InvocationHandler ih = null;
		try {
			ih = new RequestorProxy(dest,shared);

		} catch (JMSException e) {
			System.err.println(e);
		}
		return (T) Proxy.newProxyInstance(ReplicatedObjectFactory.class.getClassLoader(), new Class[] { type }, ih);
	}
}

class RequestorProxy implements InvocationHandler {
	private Requestor requestor;

	public RequestorProxy(String d,boolean shared) throws JMSException {
		requestor = new RequestorImpl(d, shared);

	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Call c = new Call(method, args);
		ObjectMessage msg = requestor.createObjectMessage();
		msg.setObject(c);
		ObjectMessage rmsg = (ObjectMessage) requestor.request(msg);
		return rmsg.getObject();
	}
}
