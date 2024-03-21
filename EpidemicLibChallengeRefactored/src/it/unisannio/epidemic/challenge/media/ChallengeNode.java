package it.unisannio.epidemic.challenge.media;

import java.util.ArrayList;

import it.unisannio.epidemic.framework.EpidemicNode;

import it.unisannio.netEmulator.Info;
import it.unisannio.netEmulator.Node;
import it.unisannio.netEmulator.Packet;

public class ChallengeNode extends EpidemicNode {

	private static final long delta = 100;

	protected ChallengeNode(Info val, Node[] net, int i) {
		super(val, net, i);
		sender = new ChallengeSender();
		receiver = new ChallengeReceiver();
	}

	public class ChallengeSender extends EpidemicSender {

		protected synchronized void infect() throws InterruptedException {
			if (!isLocked()) {
				sleep((long) (delta * Math.random()));
				atomicPushPull(value);
			}
		}
	}

	public class ChallengeReceiver extends AtomicEpidemiReceiver {

		public void processAtomicPushPull(Packet p) {
			try {
				atomicReply(value, p.senderId);
				// aggiornaStato(p);
				Info i = p.content;
				value = new Info(((double) value.val + (double) i.val) / 2, System.currentTimeMillis());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

		public void processAtomicReply(Packet p) {
			// aggiornaStato(p);
			Info i = p.content;
			value = new Info(((double) value.val + (double) i.val) / 2, System.currentTimeMillis());
		}

//		public void aggiornaStato(Packet p) {
//			Info i = p.content;
//			value = new Info(((double) value.val + (double) i.val) / 2, System.currentTimeMillis());
//		}
	}

}
