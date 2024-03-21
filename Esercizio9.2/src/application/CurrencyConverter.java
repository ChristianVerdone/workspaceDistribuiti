package application;

import javax.ejb.Stateless;

@Stateless
public class CurrencyConverter implements CurrencyConverterLocal {

	private final float convFactor = 1.166f;

	public CurrencyConverter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public float converti(float euro) {
		// TODO Auto-generated method stub
		return euro * convFactor;
	}

}
