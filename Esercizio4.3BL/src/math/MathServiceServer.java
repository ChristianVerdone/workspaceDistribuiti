package math;

import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class MathServiceServer {

	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			MathService m = new MathServiceImpl();
			Naming.bind("rmi://127.0.0.1/math", m);
			System.out.println("server started");
		} catch (AccessException e) {
			System.err.println("Bind operation not permitted");
		} catch (RemoteException e) {
			System.err.println("Remote Exception");
		} catch (MalformedURLException e) {
			System.err.println("Wrong URL for binding");			
		} catch (AlreadyBoundException e) {
			System.err.println("Object already bound to registry");
		}
	}
}