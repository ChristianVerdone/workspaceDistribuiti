package it.unisannio.epidemic.antientropy.pushPullTest;

import it.unisannio.epidemic.framework.EpidemicNode;
import it.unisannio.netEmulator.Info;
import it.unisannio.netEmulator.Node;

public class PushPullTestNode extends EpidemicNode {
	private static final long delta = 100;

	protected PushPullTestNode(Info val, Node[] net, int i) {
		super(val, net, i);
		sender = new PushPullSender();
	}

	public class PushPullSender extends EpidemicSender {

		@Override
		protected void infect() throws InterruptedException {
			pushPull(value, 1);
			sleep(delta);
		}
	}
}
