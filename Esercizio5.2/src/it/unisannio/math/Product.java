package it.unisannio.math;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "product")
public class Product {

	private double value;

	public Product() {
	};

	public Product(double d) {
		value = d;
	}

	public void setValue(double v) {
		value = v;
	}

	public double getValue() {
		return value;
	}

}
