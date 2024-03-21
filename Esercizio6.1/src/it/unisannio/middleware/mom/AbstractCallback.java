package it.unisannio.middleware.mom;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public abstract class AbstractCallback extends UnicastRemoteObject implements Callback {

	public AbstractCallback() throws RemoteException {
		super();
	}

	private static final long serialVersionUID = 1L;
}
