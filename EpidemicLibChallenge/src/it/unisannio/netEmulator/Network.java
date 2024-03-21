package it.unisannio.netEmulator;

public class Network {
	static ThreadGroup tg= new ThreadGroup("Network");
	public static int size;
	
	public static void stop() {
		tg.interrupt();
	}

}
