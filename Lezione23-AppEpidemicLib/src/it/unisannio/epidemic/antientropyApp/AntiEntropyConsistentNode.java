package it.unisannio.epidemic.antientropyApp;

import it.unisannio.epidemic.framework.EpidemicNode;
import it.unisannio.netEmulator.Info;
import it.unisannio.netEmulator.Node;

public class AntiEntropyConsistentNode extends EpidemicNode {
	private static final long delta = 100;

	protected AntiEntropyConsistentNode(Info val, Node[] net, int i) {
		super(val, net, i);
		receiver = new ConsistencyReceiver();
		sender = new ConsistencySender();
	}

	private class ConsistencyReceiver extends EpidemicReceiver {

		@Override
		protected void processInfection(Info i, int sender) throws InterruptedException {
			// TODO Auto-generated method stub
			if (i.timestap > value.timestap)
				value = i;
		}

	}

	private class ConsistencySender extends EpidemicSender {

		@Override
		protected void infect() throws InterruptedException {
			// TODO Auto-generated method stub
			push(value, 1);
			sleep(delta);
		}

	}

}
