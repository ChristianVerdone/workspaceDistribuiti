package it.unisannio.replicatedObject.test;

import it.unisannio.replicatedObject.ReplicatedObjectFactory;

public class ReplicatedTestClient {

	public static void main(String[] args) {
		ReplicatedObjectFactory<Searcher> factory = new ReplicatedObjectFactory<Searcher>(Searcher.class);
		Searcher s = factory.create("searcher");
		System.out.println(s.find("chiara"));
	}
}
