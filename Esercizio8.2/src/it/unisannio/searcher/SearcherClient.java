package it.unisannio.searcher;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class SearcherClient {

	public static final int nT = 4;

	public static void main(String[] args) {
		try {
			Searcher s = (Searcher) Naming.lookup("rmi://127.0.0.1/searcher");
			Worker[] w = new Worker[nT];

			long elapsedTime = System.currentTimeMillis();

			for (int i = 0; i < nT; i++) {
				(w[i] = new Worker(s)).start();

			}
			for (int i = 0; i < nT; i++) {
				w[i].join();
			}

			elapsedTime = System.currentTimeMillis() - elapsedTime;
			System.out.println("Tempo totale: " + elapsedTime + " ms");
			System.out.println("Throughtput: " + (nT * Worker.nR * 1000) / ((double) elapsedTime));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Worker extends Thread {
	public static final int nR = 30;
	private Searcher s;

	public Worker(Searcher s) {
		this.s = s;
	}

	public void run() {
		long elapsedTime = System.currentTimeMillis();
		for (int i = 0; i < nR; i++) {
			try {
				boolean result = s.findObject(i);

			} catch (Exception e) {
				System.err.println(e);
			}
		}
		elapsedTime = System.currentTimeMillis() - elapsedTime;
		System.out.println("Elaborazione locale al thread: " + this + " in " + elapsedTime +" ms");
		System.out.println("Tempo di risposta medio: " + elapsedTime / (double) nR + "ms");
	}
}