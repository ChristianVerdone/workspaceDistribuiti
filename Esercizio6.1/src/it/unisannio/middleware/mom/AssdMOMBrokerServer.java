package it.unisannio.middleware.mom;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class AssdMOMBrokerServer {
	public static void main(String[] args) {
		try {
			AssdMOMBroker broker = new AssdMOMBrokerTQ();
			LocateRegistry.createRegistry(1099);
			Naming.bind("rmi://localhost/assdBroker", broker);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
