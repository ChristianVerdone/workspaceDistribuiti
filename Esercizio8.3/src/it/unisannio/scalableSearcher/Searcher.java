package it.unisannio.scalableSearcher;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Searcher extends Remote {

	public boolean findObject(Object o) throws RemoteException;
}
