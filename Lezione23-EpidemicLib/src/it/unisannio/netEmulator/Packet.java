package it.unisannio.netEmulator;

public class Packet {
	public Info content;
	public int senderId;

	public Packet(Info val, int s) {
		content = val;
		senderId = s;
	}

}
