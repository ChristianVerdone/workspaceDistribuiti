package it.unisannio.epidemic.antientropy.pushPullTest;

import it.unisannio.netEmulator.Info;
import it.unisannio.netEmulator.Network;
import it.unisannio.netEmulator.Packet;

public class PushPullTestApp {
	public static final int nT = 256;

	public static void main(String[] args) {
		PushPullTestNode[] network = new PushPullTestNode[nT];
		for (int i = 0; i < nT; i++) {
			network[i] = new PushPullTestNode(new Info((double) 10.0d, 0.0), network, i);
		}
		for (int i = 0; i < nT; i++) {
			network[i].start();
		}

		for (int i = 0; i < nT; i++) {
			System.out.println(
					i + " value " + network[i].getValue().val + " timestamp " + network[i].getValue().timestamp);
		}

		System.out.println("*****************");

		try {
			network[0].setPacket(new Packet(new Info(37.0, System.nanoTime()), 0, Packet.Type.PushPull));
			Thread.sleep(2000);

			for (int i = 0; i < nT; i++) {
				System.out.println(
						i + " value " + network[i].getValue().val + " timestamp " + network[i].getValue().timestamp);
			}
			Network.stop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


}
