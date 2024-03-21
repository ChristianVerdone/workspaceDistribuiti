package it.unisannio.library;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest/library")
public class BookApplication extends Application {

	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(it.unisannio.library.BookServiceImpl.class);
		return s;
	}

	public Set<Object> getSingletons() {
		Set<Object> s = new HashSet<Object>();
		s.add(new it.unisannio.library.BookServiceImpl());
		return s;
	}

}
