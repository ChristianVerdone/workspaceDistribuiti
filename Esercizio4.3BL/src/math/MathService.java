package math;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MathService extends Remote{
	public double multiply (double a, double b) throws RemoteException;
	public double divide(double a, double b) throws RemoteException;
	public long factOf(int a) throws RemoteException;
}
