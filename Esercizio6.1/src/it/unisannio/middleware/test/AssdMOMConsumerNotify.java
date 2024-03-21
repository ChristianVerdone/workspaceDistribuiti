package it.unisannio.middleware.test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import it.unisannio.middleware.mom.AssdMOMBroker;
import it.unisannio.middleware.mom.*;

public class AssdMOMConsumerNotify {

	public static void main(String[] args) {
		AssdMOMBroker broker;
		try {
			broker = (AssdMOMBroker) Naming.lookup("rmi://localhost/assdBroker");
			broker.notify(new AbstractCallback() {
				private static final long serialVersionUID = 1L;

				@Override
				public void onMessage(Message msg) throws RemoteException {
					System.out.println(msg.getPayload());

				}
			});

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
}
