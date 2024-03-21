package it.unisannio.epidemic.challenge.media;

import it.unisannio.netEmulator.Info;
import it.unisannio.netEmulator.Network;

public class ChallengeApp {

	public static final int nT = 8;

	public static void main(String[] args) {
		ChallengeNode[] network = new ChallengeNode[nT];
		for (int i = 0; i < nT; i++) {
			network[i] = new ChallengeNode(new Info((double) 0.0d , 0.0), network, i);
		}
		network[0].setValue(new Info(1.0d, 0.0));
		
		for (int i = 0; i < nT; i++) {
			network[i].start();
		}
			
		int waitTime = 40*nT+2000;
		System.out.println("*********** " + waitTime/1000 + " Sec *********\n");
		
		try {
			Thread.sleep(waitTime);
			Network.stop();
			for (int i = 0; i < nT; i++) {
				System.out.println(
						i + " value " + network[i].getValue().val + " la rete è composta da " + Math.round(1/(double) network[i].getValue().val)+ " nodi");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
