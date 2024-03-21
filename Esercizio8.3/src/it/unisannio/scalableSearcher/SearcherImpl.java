package it.unisannio.scalableSearcher;

import java.rmi.RemoteException;

public class SearcherImpl implements Searcher {

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
