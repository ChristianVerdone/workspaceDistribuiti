package it.unisannio.middleware.mom;

import java.rmi.*;

public interface AssdMOMBroker extends Remote {

	public void put(Message msg) throws RemoteException;

	public Message get() throws RemoteException;

	public Message poll() throws MsgNotFoundException, RemoteException;

	public void notify(Callback cb) throws RemoteException;

}
