package application;

import javax.ejb.Stateless;

@Stateless
public class CurrencyConverter implements CurrencyConverterLocal, CurrencyConverterRemote {

	private final float convFactor = 1.166f;

	public CurrencyConverter() {
		// TODO Auto-generated constructor stub
	}

	public float convert(float euro) {
		// TODO Auto-generated method stub
		return euro * convFactor;
	}

}
