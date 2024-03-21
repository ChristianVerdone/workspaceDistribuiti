package it.unisannio.epidemic.antiEntropy.pushTest;

import it.unisannio.epidemic.framework.EpidemicNode;
import it.unisannio.netEmulator.Info;
import it.unisannio.netEmulator.Node;

public class PushTestNode extends EpidemicNode {
	private static final long delta = 100;

	protected PushTestNode(Info val, Node[] net, int i) {
		super(val, net, i);
		sender = new PushSender();
	}

	public class PushSender extends EpidemicSender {

		protected void infect() throws InterruptedException {
			push(value, 1);
			sleep(delta);
		}
	}
}
