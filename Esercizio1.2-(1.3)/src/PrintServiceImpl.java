import java.rmi.*;
import java.rmi.server.*;

/*Estende UnicastRemoteObject, è l’oggetto remoto e il RealSubject che implementa l’interfaccia PrintService. 
 * È il prodotto concreto generato da PrintServiceFactoryImpl. 
 */

public class PrintServiceImpl extends UnicastRemoteObject implements PrintService {
	
	private static final long serialVersionUID = 1L;
	
	public PrintServiceImpl() throws RemoteException {}
	
	public void print(String msg) throws RemoteException{
		System.out.println(msg);
		
	}
}
