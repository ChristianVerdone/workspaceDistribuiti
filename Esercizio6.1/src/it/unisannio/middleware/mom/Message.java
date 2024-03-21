package it.unisannio.middleware.mom;

import java.io.Serializable;

public interface Message extends Serializable {
	public Object getPayload();
	public long getId();
	public void setId(long id);
	public void setPayload(Object payload);
}
