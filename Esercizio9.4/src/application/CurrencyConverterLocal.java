package application;

import javax.ejb.Local;

@Local
public interface CurrencyConverterLocal {
	float converti(float euro);
}
