import java.rmi.RemoteException;
import java.io.*;

//Interfaccia di definizione di un agente, Serializzabile e Runnable, dato che 
//idealmente possiede un thread di controllo per eseguire un task in ciascun 
//contenitore. I metodi esposti sono run, start e migrateTo(Node node)

public interface Agent extends Serializable, Runnable{
	
	//L'implementazione di Run � a carico di scrive l'applicazione
	public void run();
	
	//Start e migrateTo vengono implementate in AbstractAgent
	public void start();
	
	public void migrateTo(Node node) throws RemoteException; //primitiva esposta dal container per spostare l'agente corrente sul nodo specificato

}
