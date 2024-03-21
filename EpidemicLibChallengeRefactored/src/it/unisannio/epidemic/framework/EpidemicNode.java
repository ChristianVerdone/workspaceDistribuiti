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
	private boolean locked = false;

	protected EpidemicNode(Info val, Node[] net, int i) {
		super(val, net, i);
		receiver = new EpidemicReceiver();
	}

	protected EpidemicNode(Info val, Node[] net, int i, int k) {
		super(val, net, i);
		this.k = k;
		receiver = new EpidemicReceiver();
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
			send(new Packet(val, thisId, Packet.Type.Push), peerId);
		}
	}

	protected void pull(Info val, int destN) throws InterruptedException {
		List<Integer> toExclude = new ArrayList<Integer>();
		int peerId = thisId;
		// gossip with destN nodes
		for (int j = 0; j < destN; j++) {
			toExclude.add(peerId);
			while (toExclude.contains(peerId))
				peerId = (int) (network.length * Math.random());
			send(new Packet(val, thisId, Packet.Type.Pull), peerId);
		}
	}

	protected void pushPull(Info val, int destN) throws InterruptedException {
		List<Integer> toExclude = new ArrayList<Integer>();
		int peerId = thisId;
		// gossip with destN nodes
		for (int j = 0; j < destN; j++) {
			toExclude.add(peerId);
			while (toExclude.contains(peerId))
				peerId = (int) (network.length * Math.random());
			send(new Packet(val, thisId, Packet.Type.PushPull), peerId);
		}
	}

	protected int pushPull(Info val) throws InterruptedException {
		int peerId = thisId;
		while (peerId == thisId)
			peerId = (int) (network.length * Math.random());
		send(new Packet(val, thisId, Packet.Type.PushPull), peerId);
		return peerId;
	}

	synchronized public void atomicPushPull(Info val) throws InterruptedException {
		locked = true;
		int peerId = thisId;
		while (peerId == thisId)
			peerId = (int) (network.length * Math.random());
		send(new Packet(val, thisId, Packet.Type.AtomicPushPull), peerId);
	}

	protected void reply(Info val, int peerId) throws InterruptedException {
		send(new Packet(val, thisId, Packet.Type.Reply), peerId);
	}

	synchronized protected void atomicReply(Info val, int peerId) throws InterruptedException {
		send(new Packet(val, thisId, Packet.Type.AtomicReply), peerId);
	}

	public synchronized boolean isLocked() {
		return locked;
	}

//	public synchronized void setOccupied(boolean val) {
//		locked = val;
//	}

	protected void sendFeedback(int nodeId) throws InterruptedException {
		send(new Packet(new Info("IK", 0), thisId), nodeId);
	}

	protected abstract class AtomicEpidemiReceiver extends EpidemicReceiver {
		private ArrayList<Packet> pendingPacket = new ArrayList<Packet>();

		synchronized protected void processInfection(Packet p) throws InterruptedException {
			// System.out.println(p.type+"");
			switch (p.type) {
			case Push:
				onPush(p);
				break;
			case Pull:
				onPull(p);
				break;
			case Reply:
				onReply(p);
				break;
			case PushPull:
				onPushPull(p);
				break;
			case AtomicPushPull:
				onAtomicPushPull(p);
				break;
			case AtomicReply:
				onAtomicReply(p);
				break;
			default:
				System.err.println("Packet Type Error " + p.type);
				break;
			}
		}

		public void onAtomicReply(Packet p) throws InterruptedException {
			processAtomicReply(p);
			locked = false;
			if (!pendingPacket.isEmpty())
				svuotaCoda();
		}

		public void onAtomicPushPull(Packet p) throws InterruptedException {
			if (!locked)
				processAtomicPushPull(p);
			else
				pendingPacket.add(p);
		}

		public abstract void processAtomicPushPull(Packet p);

		public abstract void processAtomicReply(Packet p);

		synchronized private void svuotaCoda() throws InterruptedException {
			for (Packet p : pendingPacket) {
				processAtomicPushPull(p);
			}
			pendingPacket.clear();

		}
	}

	protected class EpidemicReceiver extends Receiver {
		public void run() {
			while (!isInterrupted()) {
				try {
					Packet p = receive();
					if (p.content.val instanceof String && p.content.val.equals("IK")) {
						if (state != EpidemicNode.States.Removed)
							if (Math.random() < 1.0 / k)
								state = EpidemicNode.States.Removed;
					} else {
						// System.out.println("pacchetto "+ p.type);
						processInfection(p);
					}
				} catch (InterruptedException e) {
					interrupt();
				}
			}

		}

		synchronized protected void processInfection(Packet p) throws InterruptedException {
			// System.out.println(p.type+"");
			switch (p.type) {
			case Push:
				onPush(p);
				break;
			case Pull:
				onPull(p);
				break;
			case Reply:
				onReply(p);
				break;
			case PushPull:
				onPushPull(p);
				break;
			default:
				System.err.println("Packet Type Error " + p.type);
				break;
			}
		}

		public void onPush(Packet p) {
			Info i = p.content;
			if (value.timestamp < i.timestamp) {
				value = i;
			}
		}

		public void onPull(Packet p) throws InterruptedException {
			Info i = p.content;
			if (value.timestamp > i.timestamp) {
				reply(value, p.senderId);
			}
		}

		public void onReply(Packet p) {
			Info i = p.content;
			if (value.timestamp < i.timestamp) {
				value = i;
			}
		}

		public void onPushPull(Packet p) throws InterruptedException {
			Info i = p.content;
			if (value.timestamp < i.timestamp) {
				value = i;
			} else if (value.timestamp > i.timestamp) {
				reply(value, p.senderId);
			}
		}
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