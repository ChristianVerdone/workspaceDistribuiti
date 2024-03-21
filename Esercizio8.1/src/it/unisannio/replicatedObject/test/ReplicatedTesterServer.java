package it.unisannio.replicatedObject.test;

import javax.jms.JMSException;

import it.unisannio.replicatedObject.ReplicatedObject;

public class ReplicatedTesterServer {
	public static void main(String[] args) {
		try {
			new ReplicatedObject<Searcher>(new SearcherImpl(), "searcher").start();
		} catch (JMSException e) {
			System.err.println(e);
		}
	}
}
