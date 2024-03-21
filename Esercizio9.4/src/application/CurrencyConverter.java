package application;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Stateless
public class CurrencyConverter implements CurrencyConverterLocal {

	private final float convFactor = 1.166f;

	public CurrencyConverter() {
		// TODO Auto-generated constructor stub
	}

	@RolesAllowed({"employer","admin"})
	@Interceptors(ProfileInterceptor.class)
	public float converti(float euro) {
		// TODO Auto-generated method stub
		return euro * convFactor;
	}

}
