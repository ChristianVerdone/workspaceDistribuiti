package math;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.jws.WebService;

@WebService(targetNamespace = "http://math/", endpointInterface = "math.MathService", portName = "MathServiceImplPort", serviceName = "MathServiceImplService")
public class MathServiceImpl implements MathService {
	private MathService m;

	protected MathServiceImpl() {
		try {
			m = (MathService) Naming.lookup("rmi://127.0.0.1/math");

		} catch (MalformedURLException e) {
			System.err.println("Wrong URL for binding");
		} catch (RemoteException e) {
			System.err.println("Registry could not be contacted");
		} catch (NotBoundException e) {
			System.err.println("Object not bound to registry");
		}
	}

	@Override
	public double multiply(double a, double b) {
		double result = Double.MAX_VALUE;
		if (m != null) {
			result = m.multiply(a, b);
		}
		return result;
	}

	@Override
	public double divide(double a, double b) {
		double result = Double.MAX_VALUE;
		if (m != null) {
			result = m.divide(a, b);
		}
		return result;
	}

	@Override
	public long factOf(int a) {
		long result = Long.MAX_VALUE;
		if (m != null) {
			result = m.factOf(a);
		}
		return result;
	}
}
