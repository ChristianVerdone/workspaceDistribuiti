package presentation;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest")
public class RestApplication extends Application {

	public Set<Class<?>> getClasses(){
		Set<Class<?>> s= new HashSet<Class<?>>();
		s.add(presentation.ConverterController.class);
		
		return s;
	}
	
	public Set<Object> getSingletons(){
		Set<Object> s= new HashSet<Object>();
		s.add(new presentation.ConverterController());
		return s;
	}
}

