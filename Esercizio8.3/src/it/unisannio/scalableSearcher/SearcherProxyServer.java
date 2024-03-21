package it.unisannio.scalableSearcher;

import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.jms.JMSException;

public class SearcherProxyServer {

	public static void main(String[] args) throws JMSException {

		try {
			LocateRegistry.createRegistry(1099);
			Searcher s = new SearcherProxy();
			Naming.bind("rmi://127.0.0.1/searcher", s);

		} catch (AccessException e) {
			System.err.println("Bind operation not permitted");
		} catch (RemoteException e) {
			System.err.println("Registry could not be contacted");
		} catch (MalformedURLException e) {
			System.err.println("Wrong URL for binding");
		} catch (AlreadyBoundException e) {
			System.err.println("Object alreay bound to the registry");
		}
	}

}
