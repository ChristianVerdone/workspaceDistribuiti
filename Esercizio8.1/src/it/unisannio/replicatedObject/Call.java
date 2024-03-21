package it.unisannio.replicatedObject;

import java.io.Serializable;
import java.lang.reflect.Method;

public class Call implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String methodName;
	public Class<?>[] argTypes;
	public Object[] arguments;

	public Call(Method m, Object[] args) {
		argTypes = m.getParameterTypes();
		methodName = m.getName();
		arguments = args;
	}
}
