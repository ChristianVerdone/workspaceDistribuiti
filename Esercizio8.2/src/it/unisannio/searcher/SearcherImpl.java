package it.unisannio.searcher;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SearcherImpl extends UnicastRemoteObject implements Searcher {
	private static final long serialVersionUID = 1L;

	public SearcherImpl() throws RemoteException {
	}

	@Override
	public boolean findObject(Object o) throws RemoteException {
		int in = (Integer) o;
		boolean result = ((in % 2) == 0);
		for (long i = 0; i < 100000L; i++)
			for (long j = 0; j < 100L; j++)
				;
		return result;
	}

}
