package it.unisannio.epidemic.framework;

import java.util.ArrayList;
import java.util.List;

import it.unisannio.netEmulator.Info;
import it.unisannio.netEmulator.Node;
import it.unisannio.netEmulator.Packet;

public abstract class EpidemicNode extends Node {
	protected enum States {
		Susceptible, Infected, Removed
	};

	protected States state = States.Susceptible;
	protected int k = 10;

	protected EpidemicNode(Info val, Node[] net, int i) {
		super(val, net, i);
	}

	protected EpidemicNode(Info val, Node[] net, int i, int k) {
		super(val, net, i);
		this.k = k;
	}

	protected EpidemicNode(Info val, Node[] net, int i, Sender s, Receiver r) {
		super(val, net, i, s, r);
	}

	protected void push(Info val, int destN) throws InterruptedException {
		List<Integer> toExclude = new ArrayList<Integer>();
		int peerId = thisId;
		// gossip with destN nodes
		for (int j = 0; j < destN; j++) {
			toExclude.add(peerId);
			while (toExclude.contains(peerId))
				peerId = (int) (network.length * Math.random());
			send(new Packet(val, thisId), peerId);
		}
	}

	protected void sendFeedback(int nodeId) throws InterruptedException {
		send(new Packet(new Info("IK", 0), thisId), nodeId);
	}

	protected abstract class EpidemicReceiver extends Receiver {
		public void run() {
			while (!isInterrupted()) {
				try {
					Packet p = receive();
					if (p.content.val instanceof String && p.content.val.equals("IK")) {
						if (state != EpidemicNode.States.Removed)
							if (Math.random() < 1.0 / k)
								state = EpidemicNode.States.Removed;
					} else
						processInfection(p.content, p.senderId);
				} catch (InterruptedException e) {
					interrupt();
				}
			}

		}

		protected abstract void processInfection(Info i, int sender) throws InterruptedException;
	}

	protected abstract class EpidemicSender extends Sender {
		public void run() {
			while (!isInterrupted()) {
				try {
					infect();
				} catch (InterruptedException e) {
					interrupt();
				}

			}

		}

		protected abstract void infect() throws InterruptedException;
	}
}