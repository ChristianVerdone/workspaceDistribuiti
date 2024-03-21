package application;

import javax.ejb.Remote;

@Remote
public interface CurrencyConverterRemote {
float convert(float euro);
}
