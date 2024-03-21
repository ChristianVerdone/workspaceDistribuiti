package math;

import javax.jws.WebService;

@WebService(targetNamespace = "http://math/", endpointInterface = "math.Math", portName = "MathServiceImplPort", serviceName = "MathServiceImplService")
public class MathServiceImpl implements Math {
	@Override
	public double multiply(double a, double b) {
		// TODO Auto-generated method stub
		return a*b;
	}

	@Override
	public double divide(double a, double b) {
		// TODO Auto-generated method stub
		return a/b;
	}

	@Override
	public long factOf(int a) {
		// TODO Auto-generated method stub
		return a <= 1 ? 1 : a * factOf(a - 1);
	}
}
