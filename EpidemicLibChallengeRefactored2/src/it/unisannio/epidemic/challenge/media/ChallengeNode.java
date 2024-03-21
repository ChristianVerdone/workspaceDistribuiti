package it.unisannio.epidemic.challenge.media;

import it.unisannio.epidemic.framework.EpidemicNode;

import it.unisannio.netEmulator.Info;
import it.unisannio.netEmulator.Node;
import it.unisannio.netEmulator.Packet;

public class ChallengeNode extends EpidemicNode {
	private long timeout = 0;
	private static final long delta = 10;

	protected ChallengeNode(Info val, Node[] net, int i) {
		super(val, net, i);
		sender = new ChallengeSender();
		receiver = new ChallengeReceiver();
	}

	public class ChallengeSender extends EpidemicSender {

		synchronized protected void infect() throws InterruptedException {
			long time = System.currentTimeMillis();

			if (!isLocked()) {
				timeout = (long) (time + delta + delta * Math.random());
				atomicPushPull(value, time);
				sleep(delta/4);
			}
			if (time > timeout) {
				setLocked(false);
				setCodaDaSvuotare(true);
			}
		}
	}

	public class ChallengeReceiver extends AtomicEpidemiReceiver {

		public void processAtomicPushPull(Packet p) {
			try {
				atomicReply(value, p.senderId, p.content.timestamp);
				aggiornaStato(p);
			} catch (InterruptedException e) {
				interrupt();
			}

		}

		public void processAtomicReply(Packet p) {
			aggiornaStato(p);
		}

		public void aggiornaStato(Packet p) {
			Info i = p.content;
			value = new Info(((double) value.val + (double) i.val) / 2, p.content.timestamp);
		}
	}

}
