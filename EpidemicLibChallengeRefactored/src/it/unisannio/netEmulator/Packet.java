package it.unisannio.netEmulator;

public class Packet {

	public static enum Type {
		NoType, Push, Pull, PushPull, Reply, AtomicPushPull, AtomicReply
	};

	public Info content;
	public int senderId;
	public Type type = Type.NoType;

	public Packet(Info val, int s) {
		this.content = val;
		this.senderId = s;
	}

	public Packet(Info val, int senderId, Type type) {
		this(val, senderId);
		this.type = type;
	}
}
