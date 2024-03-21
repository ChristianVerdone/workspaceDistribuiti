package it.unisannio.middleware.mom;

public class MessageImpl implements Message {
	private static final long serialVersionUID = 1L;
	private long id;
	private Object payload;

	public MessageImpl(Object line) {
		this.payload = line;
	}

	@Override
	public Object getPayload() {
		return payload;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	@Override
	public void setPayload(Object payload) {
		this.payload = payload;
	}

}
