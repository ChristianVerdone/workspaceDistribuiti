package math;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MathServiceImpl extends UnicastRemoteObject implements MathService {
	
	protected MathServiceImpl()throws RemoteException{
		super();
	}
	
	private static final long serialVersionUID=1L;
	
	public double multiply(double a, double b) {
		// TODO Auto-generated method stub
		return a*b;
	}

	public double divide(double a, double b) {
		// TODO Auto-generated method stub
		return a/b;
	}

	public long factOf(int a) {
		// TODO Auto-generated method stub
		return a <= 1 ? 1 : a * factOf(a - 1);
	}
}