package it.unisannio.epidemic.search;

import it.unisannio.netEmulator.Info;
import it.unisannio.netEmulator.Network;
import it.unisannio.netEmulator.Packet;

public class PushSearchApp {

	public static final int nT = 256;
	public static final int k = 100;

	public static void main(String[] args) {
		PushSearchNode[] network = new PushSearchNode[nT];
		for (int i = 0; i < nT; i++) {
			network[i] = new PushSearchNode(new Info((double) i, 0), network, i, k);
		}
		for (int i = 0; i < nT; i++) {
			network[i].start();
		}
		try {
			network[0].setPacket(new Packet(new Info(100.0, System.nanoTime()), 0, Packet.Type.Push));
			Thread.sleep(2000);

			Network.stop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
