import java.rmi.*;
import java.rmi.server.*;

//Estende UnicastRemoteObject per associare un thread agli oggetti che sono istanze di questa classe, 
//per fare in modo che il Server non termini l'esecuzione,
//Definiamo il costruttore senza argomenti e il metodo previsto nell'interfaccia PrintService


public class PrintServiceImpl extends UnicastRemoteObject implements PrintService {
	
	//Viene usato per differenziare diverse implementazioni della stessa interfaccia
	private static final long serialVersionUID = 1L;
	
	public PrintServiceImpl() throws RemoteException {}
	
	public void print(String msg) throws RemoteException{
		System.out.println(msg);
		
	}
}
