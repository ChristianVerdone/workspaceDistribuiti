package it.unisannio.epidemic.search;

import it.unisannio.epidemic.framework.EpidemicNode;
import it.unisannio.netEmulator.Info;
import it.unisannio.netEmulator.Node;
import it.unisannio.netEmulator.Packet;

public class PushSearchNode extends EpidemicNode {

	protected PushSearchNode(Info val, Node[] net, int i, int k) {
		super(val, net, i, k);
		receiver = new PushReceiver();
		sender = null;
	}

	public class PushReceiver extends EpidemicReceiver {

		public void onPush(Packet p) {
			Info i = p.content;
			try {
				if (state != EpidemicNode.States.Removed) {
					if (state == EpidemicNode.States.Infected) {
						sendFeedback(p.senderId);
					}
					if ((double) i.val == (double) value.val) {
						System.out.println("value found: " + value.val + " node " + thisId);
					}
					state = States.Infected;
					push(i, 1);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
