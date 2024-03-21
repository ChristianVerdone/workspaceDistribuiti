package it.unisannio.math;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest/app2")
public class MultiplyApplication extends Application {

	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(it.unisannio.math.MultiplierImpl.class);
		return s;
	}

	public Set<Object> getSingletons() {
		Set<Object> s = new HashSet<Object>();
		s.add(new it.unisannio.math.MultiplierImpl());
		return s;
	}

}
