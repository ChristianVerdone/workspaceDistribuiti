package it.unisannio.middleware.test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import it.unisannio.middleware.mom.AssdMOMBroker;
import it.unisannio.middleware.mom.Message;
import it.unisannio.middleware.mom.MessageImpl;

public class AssdMOMContinuousProducer {
	public static void main(String[] args) {
System.out.println("start");
		try {
			AssdMOMBroker broker = (AssdMOMBroker) Naming.lookup("rmi://localhost/assdBroker");
			int n = 100;
			for (int i = 0; i < n; i++) {
				String line = "Message : " + i;
				Message msg = new MessageImpl(line);
				broker.put(msg);
			}
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
}
