package it.unisannio.epidemic.rumorMongering;

import it.unisannio.netEmulator.Info;
import it.unisannio.netEmulator.Network;
import it.unisannio.netEmulator.Packet;

public class RumorMongeringAverageApp {
	public static final int nT = 30;

	public static void main(String[] args) {
		RumorMongeringAverageNode[] network = new RumorMongeringAverageNode[nT];

		for (int i = 0; i < nT; i++) {
			network[i] = new RumorMongeringAverageNode(new Info((double) 0.0, 0.0), network, i,100000);
		}

		try {
			network[1].setPacket(new Packet(new Info(1.0d, 0.0), 0));
			for (int i = 0; i < nT; i++) {
				network[i].start();
			}
			Thread.sleep(3000);
			Network.stop();

			for (int i = 0; i < nT; i++) {
				System.out.println(i + " value " + network[i].getValue().val + " la rete è composta da "
						+ Math.round(1 / (double) network[i].getValue().val));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
