package it.unisannio.middleware.mom;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Callback extends Remote {
	public void onMessage(Message msg) throws RemoteException;
}
