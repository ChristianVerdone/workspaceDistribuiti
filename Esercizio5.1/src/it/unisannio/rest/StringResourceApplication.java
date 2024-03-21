package it.unisannio.rest;


import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest/app1")
public class StringResourceApplication extends Application {
	public Set<Class<?>> getClasses(){
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(it.unisannio.rest.StringResourceImpl.class);
		return s;
	}
	public Set<Object> getSingletons(){
		Set<Object> s = new HashSet<Object>();
		s.add(new it.unisannio.rest.StringResourceImpl());
		return s;
	}
}
