package math;

import javax.jws.WebService;

@WebService(name = "Math", targetNamespace = "http://math/")
public interface Math {
	public double multiply (double a, double b);
	public double divide(double a, double b);
	public long factOf(int a);
}
