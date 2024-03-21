package it.unisannio.scalableRESTSearcher;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


@ApplicationPath("/rest")
public class SearcherApplication extends Application {
	public Set<Class<?>> getClasses() {
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(it.unisannio.scalableRESTSearcher.SearcherProxy.class);
		return s;
	}

	public Set<Object> getSingletons() {
		Set<Object> s = new HashSet<Object>();
		s.add(new it.unisannio.scalableRESTSearcher.SearcherProxy());

		return s;
	}
}
