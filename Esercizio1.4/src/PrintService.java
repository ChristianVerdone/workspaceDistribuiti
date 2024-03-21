import java.rmi.Remote;
import java.rmi.RemoteException;

//L'operatore new non pu� essere utilizzato per creare oggetti remoti rispetto al
//nodo sui cui new � eseguito, non possiamo pensare di creare un oggetto remoto dal processo A
//su un altro processo del sistema distribuito usando l'operatore new su A, non esiste l'equivalente remoto di new


//Interfaccia che estende Remote ed espone il prototipo del metodo di stampa.
//L�oggetto remoto che la implementa dovr� implementarne il corpo.

public interface PrintService extends Remote {

    public void print(String msg) throws RemoteException;

    public void print(User u) throws RemoteException;


}
