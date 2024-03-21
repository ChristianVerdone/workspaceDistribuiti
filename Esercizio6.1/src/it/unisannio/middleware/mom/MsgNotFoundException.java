package it.unisannio.middleware.mom;

public class MsgNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public MsgNotFoundException(String msg) {
		super(msg);
	}
}
