package it.unisannio.epidemic.rumorMongering;

import it.unisannio.epidemic.framework.EpidemicNode;

import it.unisannio.netEmulator.Info;
import it.unisannio.netEmulator.Node;
import it.unisannio.netEmulator.Packet;

public class RumorMongeringAverageNode extends EpidemicNode {

	protected RumorMongeringAverageNode(Info val, Node[] net, int i, int k) {
		super(val, net, i, k);
		receiver = new AverageReceiver();
		sender = null; // new AverageSender();
	}

	private class AverageReceiver extends EpidemicReceiver {
		protected void processInfection(Packet p) throws InterruptedException {
				Info i = p.content;
				if (state != EpidemicNode.States.Removed) {
					if (state == EpidemicNode.States.Infected) {
						sendFeedback(p.senderId);
					}
					value.val = ((double) value.val + (double) i.val) / 2;
					reply(new Packet(value, thisId), p.senderId);
					if (value.val.equals(i.val))
						state = EpidemicNode.States.Infected;
					push(value, 1);
			}
			
		}

	}

	
}
