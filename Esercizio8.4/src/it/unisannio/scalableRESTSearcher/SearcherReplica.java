package it.unisannio.scalableRESTSearcher;

import java.rmi.RemoteException;

import javax.jms.JMSException;

import it.unisannio.replicatedObject.ReplicatedObject;

public class SearcherReplica {

	public static void main(String[] args) {
		try {
			new ReplicatedObject<Searcher>(new SearcherImpl(), "searcher2", true).start();
		} catch (JMSException | RemoteException e) {
			System.err.println(e);
		}
	}

}
