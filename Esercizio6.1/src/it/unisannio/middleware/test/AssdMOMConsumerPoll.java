package it.unisannio.middleware.test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import it.unisannio.middleware.mom.AssdMOMBroker;
import it.unisannio.middleware.mom.Message;
import it.unisannio.middleware.mom.MsgNotFoundException;

public class AssdMOMConsumerPoll {

	public static void main(String[] args) throws MsgNotFoundException {
		try {
			AssdMOMBroker broker = (AssdMOMBroker) Naming.lookup("rmi://localhost/assdBroker");
			while (true) {
				try {
					Message msg = broker.poll();
					System.out.println(msg.getPayload());
					break;
				} catch (MsgNotFoundException e) {
					System.out.println("polling");
				}
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}

	}
}
