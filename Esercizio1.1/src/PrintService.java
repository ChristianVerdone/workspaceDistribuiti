import java.rmi.*;

//Interfaccia che estende Remote ed espone il prototipo del metodo di stampa. 
//L’oggetto remoto che la implementa dovrà implementarne il corpo

//Definisce l'interfaccia PrintService che estende Remote
public interface PrintService extends Remote {
 // Dichiarazione del metodo di stampa
 public void print(String msg) throws RemoteException;
}