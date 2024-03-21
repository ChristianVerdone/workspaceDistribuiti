package it.unisannio.scalableRESTSearcher;

import java.rmi.RemoteException;

import it.unisannio.replicatedObject.ReplicatedObjectFactory;

public class SearcherProxy implements Searcher{
	private Searcher searcher;
	
	public SearcherProxy() {
		ReplicatedObjectFactory<Searcher> factory = new ReplicatedObjectFactory<Searcher>(Searcher.class, true);
		searcher = factory.create("searcher2");
	}

	@Override
	public String find(String s) throws RemoteException {
		return searcher.find(s);
	
 
	}
}
