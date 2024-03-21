import java.rmi.*;

//L'operatore new non può essere utilizzato per creare oggetti remoti rispetto al
//nodo sui cui new è eseguito, non possiamo pensare di creare un oggetto remoto dal processo A
//su un altro processo del sistema distribuito usando l'operatore new su A, non esiste l'equivalente remoto di new

//Interfaccia che estende Remote ed espone il prototipo del metodo di stampa. 
//L’oggetto remoto che la implementa dovrà implementarne il corpo.

public interface PrintService extends Remote {
	public void print(String msg) throws RemoteException;
}
