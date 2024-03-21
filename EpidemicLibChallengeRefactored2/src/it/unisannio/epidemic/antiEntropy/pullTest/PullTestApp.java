package it.unisannio.epidemic.antiEntropy.pullTest;

import it.unisannio.netEmulator.Info;
import it.unisannio.netEmulator.Network;
import it.unisannio.netEmulator.Packet;

public class PullTestApp {
	public static final int nT = 30;

	public static void main(String[] args) {
		PullTestNode[] network = new PullTestNode[nT];
		for (int i = 0; i < nT; i++) {
			network[i] = new PullTestNode(new Info((double) 10.0d, 0), network, i);
		}
		for (int i = 0; i < nT; i++) {
			network[i].start();
		}
		
		for (int i = 0; i < nT; i++) {
			System.out.println(
					i + " value " + network[i].getValue().val + " timestamp " + network[i].getValue().timestamp);
		}
		
		System.out.println("\n********************\n");
		
		
		try {
			network[0].setPacket(new Packet(new Info(37.0, System.currentTimeMillis()), 0, Packet.Type.Reply));
			Thread.sleep(2000);
			Network.stop();
			for (int i = 0; i < nT; i++) {
				System.out.println(
						i + " value " + network[i].getValue().val + " timestamp " + network[i].getValue().timestamp);
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
