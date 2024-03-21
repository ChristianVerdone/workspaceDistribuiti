package it.unisannio.scalableSearcher;

import java.rmi.RemoteException;

import javax.jms.JMSException;

import it.unisannio.replicatedObject.ReplicatedObject;

public class SearcherReplica {
	public static void main(String[] args) throws RemoteException{
		try {
			new ReplicatedObject<Searcher>(new SearcherImpl(), "searcher1", true).start();
		} catch (JMSException | RemoteException e) {
			System.err.println(e);
		}
	}
}
