import java.rmi.*;

//Interfaccia che estende Remote ed espone i prototipi del calcolo del fattoriale in maniera sincrona e asincrona.

public interface Math extends Remote{
	
	public int getFactOf(int v ) throws RemoteException;
	
	public void getAsyncFactOf(int v, CallBack continuation) throws RemoteException;
}
