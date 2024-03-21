package it.unisannio.epidemic.antientropy.pullTest;

import it.unisannio.epidemic.framework.EpidemicNode;
import it.unisannio.netEmulator.Info;
import it.unisannio.netEmulator.Node;
import it.unisannio.netEmulator.Packet;

public class PullTestNode extends EpidemicNode {
	private static final long delta = 100;

	protected PullTestNode(Info val, Node[] net, int i) {
		super(val, net, i);
		sender = new PullSender();
	}

	public class PullSender extends EpidemicSender {

		@Override
		protected void infect() throws InterruptedException {
			pull(value, 1);
			sleep(delta);
		}
	}
}
