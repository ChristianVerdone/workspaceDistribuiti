package math;

import javax.jws.WebService;

@WebService(name = "MathService", targetNamespace = "http://math/")
public interface MathService {
	public double multiply (double a, double b);
	public double divide(double a, double b);
	public long factOf(int a);
}
