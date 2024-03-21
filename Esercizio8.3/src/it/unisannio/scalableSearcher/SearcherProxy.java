package it.unisannio.scalableSearcher;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.jms.JMSException;

import it.unisannio.replicatedObject.ReplicatedObjectFactory;

public class SearcherProxy extends UnicastRemoteObject implements Searcher{
	private static final long serialVersionUID = 1L;
	private Searcher searcher;

	@Override
	public boolean findObject(Object o) throws RemoteException {
			return searcher.findObject(o);
	}
	
	public SearcherProxy() throws RemoteException, JMSException{
		ReplicatedObjectFactory<Searcher> factory = new ReplicatedObjectFactory<Searcher>(Searcher.class, true);
		searcher = factory.create("searcher1");
	}

}
