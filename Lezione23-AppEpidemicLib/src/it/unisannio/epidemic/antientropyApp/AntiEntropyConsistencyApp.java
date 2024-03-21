package it.unisannio.epidemic.antientropyApp;

import it.unisannio.netEmulator.Info;
import it.unisannio.netEmulator.Network;
import it.unisannio.netEmulator.Packet;

public class AntiEntropyConsistencyApp {
	public static final int nT = 256;

	public static void main(String[] args) {
		AntiEntropyConsistentNode[] network = new AntiEntropyConsistentNode[nT];
		for (int i = 0; i < nT; i++) {
			network[i] = new AntiEntropyConsistentNode(new Info((double) 10.0d, 0.0), network, i);
		}
		for (int i = 0; i < nT; i++) {
			network[i].start();
		}
		try {
			network[0].setPacket(new Packet(new Info(37.0, System.nanoTime()), 0));
			Thread.sleep(2000);

			for (int i = 0; i < nT; i++) {
				System.out.println(
						i + " value " + network[i].getValue().val + " timestamp " + network[i].getValue().timestap);
			}
			Network.stop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}