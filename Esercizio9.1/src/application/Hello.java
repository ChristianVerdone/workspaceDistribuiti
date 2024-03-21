package application;

import javax.ejb.Stateless;

@Stateless
public class Hello implements HelloLocal {

	public Hello() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String sayHello() {
		return "Hello from EJB World";
	}

}
