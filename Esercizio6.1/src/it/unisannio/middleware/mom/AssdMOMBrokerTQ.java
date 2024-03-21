package it.unisannio.middleware.mom;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class AssdMOMBrokerTQ extends UnicastRemoteObject implements AssdMOMBroker {
	private static final long serialVersionUID = 1L;
	private BlockingQueue<Message> queue = new LinkedBlockingQueue<Message>();
	private Scheduler scheduler = new SRRScheduler(queue); // scheduler sequenziale

	public AssdMOMBrokerTQ() throws RemoteException {
		super();
	}

	@Override
	public void put(Message msg) throws RemoteException {
		try {
			queue.put(msg);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Message get() throws RemoteException {
		try {
			return queue.take();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} // bloccante
		return null;
	}

	@Override
	public Message poll() throws MsgNotFoundException, RemoteException {
		return queue.remove();
	}

	@Override
	public void notify(Callback cb) throws RemoteException {
		scheduler.schedule(cb);
	}

}
