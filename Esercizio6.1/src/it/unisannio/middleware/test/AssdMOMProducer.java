package it.unisannio.middleware.test;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import it.unisannio.middleware.mom.AssdMOMBroker;
import it.unisannio.middleware.mom.Message;
import it.unisannio.middleware.mom.MessageImpl;

public class AssdMOMProducer {
	public static void main(String[] args) {

		try {
			AssdMOMBroker broker = (AssdMOMBroker) Naming.lookup("rmi://localhost/assdBroker");
			Scanner sc = new Scanner(System.in);
			System.out.println("Inserisci una stringa");
			String line = sc.nextLine();
			Message msg = new MessageImpl(line);
			broker.put(msg);
			sc.close();
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
}
