package it.unisannio.scalableRESTSearcher;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SearcherImpl extends UnicastRemoteObject implements Searcher {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SearcherImpl() throws RemoteException {

	}

	@Override
	public String find(String key) {
		String in = (String) key;
		boolean result = in.length() % 2 == 0;

		for (long i = 0; i < 1000000L; i++) {
			for (long j = 0; j < 100L; j++)
				;
		}
		return Boolean.toString(result);
	}

}
