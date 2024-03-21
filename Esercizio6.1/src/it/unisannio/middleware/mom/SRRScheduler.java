package it.unisannio.middleware.mom;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class SRRScheduler extends Thread implements Scheduler {
	private BlockingQueue<Message> queue;
	public List<Callback> callbacks = new ArrayList<Callback>();
	private int rrIndex; //indice di round robin che indica quale callback è stata usata per ultima

	public SRRScheduler(BlockingQueue<Message> q) {
		queue = q;
		start();
	}

	public void run() {
		try {
			Callback scheduledCallback;
			while (!isInterrupted()) {
				synchronized (callbacks) {
					while (callbacks.isEmpty())
						callbacks.wait();
					rrIndex = (rrIndex + 1) % callbacks.size();
					scheduledCallback = callbacks.get(rrIndex);
				}
				Message msg = queue.take();
				try {
					scheduledCallback.onMessage(msg);
				} catch (RemoteException e) { // Exception
					synchronized (callbacks) {
						callbacks.remove(rrIndex);
					}
					queue.put(msg); // Possiamo utilizzare BlockingDequeue che ci consente di inserire in testa
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void schedule(Callback cb) {
		synchronized (callbacks) {
			callbacks.add(cb);
			callbacks.notifyAll();
		}
	}

}
