package it.unisannio.epidemic.challenge.media;

import it.unisannio.epidemic.framework.EpidemicNode;

import it.unisannio.netEmulator.Info;
import it.unisannio.netEmulator.Node;
import it.unisannio.netEmulator.Packet;

public class AverageChallengeNode extends EpidemicNode {

	private static final long delta = 200;
	private boolean occupied = false;
	private Integer sync = null;
	
	protected AverageChallengeNode(Info val, Node[] net, int i) {
		super(val, net, i);
		sender = new ChallengeSender();
		receiver = new ChallengeReceiver();
	}

	public class ChallengeSender extends EpidemicSender {

		protected synchronized void infect() throws InterruptedException {	
			if(!occupied) {
				wait((int) (delta*Math.random()));
				sync = pushPull(value);
				occupied = true;
			}
		}
	}

	public class ChallengeReceiver extends EpidemicReceiver {

		protected synchronized void processInfection(Packet p) throws InterruptedException {
			if(p.type == Packet.Type.PushPull) {
				onPushPull(p);
			}
			if(p.type == Packet.Type.Reply) {
				onReply(p);
			}
		}

		public void onPushPull(Packet p) throws InterruptedException {
			reply(value, p.senderId);
			aggiornaStato(p);
			synchronized (AverageChallengeNode.this.sender) {
				AverageChallengeNode.this.sender.notify();
				
			}
		}

		public void onReply(Packet p) {
			aggiornaStato(p);
		}

		public void aggiornaStato(Packet p) {
			Info i = p.content;
			value = new Info((((double) value.val + (double) i.val) / 2) , System.currentTimeMillis());
		}

	}
}
