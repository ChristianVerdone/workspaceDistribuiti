package it.unisannio.epidemic.gossipSearch;

import it.unisannio.epidemic.framework.EpidemicNode;
import it.unisannio.netEmulator.Info;
import it.unisannio.netEmulator.Node;

public class GossipSearchNode extends EpidemicNode {

	protected GossipSearchNode(Info val, Node[] net, int i, int k) {
		super(val, net, i, k);
		receiver = new GossipReceiver();
		sender = null;
	}

	private class GossipReceiver extends EpidemicReceiver {

		@Override
		protected void processInfection(Info i, int sender) throws InterruptedException {
			if (state != EpidemicNode.States.Removed) {
				if (state == EpidemicNode.States.Infected) {
					sendFeedback(sender);
				}
				if ((double) i.val == (double) value.val) {
					System.out.println("value found: " + value.val + " node " + thisId);
				}
				state = States.Infected;
				push(i, 2);
			}

		}

	}

}
