package it.unisannio.math;

public class MultiplierImpl implements Multiplier {

	@Override
	public Product multiply(float a, float b) {

		return new Product(a * b);
	}

}
