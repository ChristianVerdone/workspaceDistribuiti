package it.unisannio.netEmulator;

public class Packet {
	public static enum Type {
		NoType, Push, Pull, PushPull, Reply, AtomicPushPull, AtomicReply
	};
	
	public Info content;
	public int senderId;
	public Type type = Type.NoType;
	
	public Packet(Info content, int senderId) {
		this.content = content;
		this.senderId = senderId;
	}
	
	public Packet(Info content, int senderId, Type type) {
		this(content, senderId);
		this.type=type;
	}

}
