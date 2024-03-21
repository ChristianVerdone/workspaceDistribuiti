package it.unisannio.middleware.test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import it.unisannio.middleware.mom.AssdMOMBroker;
import it.unisannio.middleware.mom.Message;

public class AssdMOMConsumerGet {
	public static void main(String[] args) {
		try {
			AssdMOMBroker broker = (AssdMOMBroker) Naming.lookup("rmi://localhost/assdBroker");
			Message msg = broker.get();
			System.out.println(msg.getPayload());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
