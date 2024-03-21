package it.unisannio.epidemic.gossipSearch;

import it.unisannio.netEmulator.*;

public class GossipSearchApp {
	protected static final int nT = 100;

	public static void main(String[] args) {
		GossipSearchNode[] network = new GossipSearchNode[nT];
		// Initial network configuration
		for (int i = 0; i < nT; i++)
			network[i] = new GossipSearchNode(new Info((double) i, 0), network, i, 10);

		// Network start
		for (int i = 0; i < nT; i++)
			network[i].start();

		try {
			// Node 0 setting
			network[0].setPacket(new Packet(new Info(30.0, 0.0), 0));
			Thread.sleep(2000);

			Network.stop(); // Stop the network
		} catch (Exception e) {
		}
	}
}