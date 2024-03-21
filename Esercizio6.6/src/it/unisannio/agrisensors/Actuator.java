package it.unisannio.agrisensors;

import javax.sql.rowset.spi.SyncFactory;

import org.apache.activemq.leveldb.replicated.groups.ZKClient.State;

interface Action {
	public static enum States {
		on, off
	};

	public String getActionName();

	public void on();

	public void off();

	public States getState();
}

abstract class VirtualAction implements Action {
	private States state = States.off;

	public void on() {
		System.out.println("Switch on " + getActionName());
		state = States.on;
	}

	public void off() {
		System.out.println("Switch off " + getActionName());
		state = States.off;
	}

	public States getState() {
		return state;
	}
}

class Actuator extends Thread {
	private Action action;

	public Actuator(Action action) {
		this.action = action;
	}

	public void run() {
		while (true) {
			try {
				synchronized (this) {
					wait();
				}
			} catch (InterruptedException e) {
			}
			if (action.getState() == Action.States.off) {
				action.on();
			}
			try {
				synchronized (this) {
					wait(1500);
					action.off();
				}
			} catch (InterruptedException e) {
				interrupt();
			}
		}
	}

	public synchronized void actuate() {
		interrupt();
	}
}
