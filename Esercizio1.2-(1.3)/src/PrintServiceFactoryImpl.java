import java.rmi.*;
import java.rmi.server.*;

/* una factory concreta che restituirï¿½ un oggetto concreto che appartiene alla classe 
di prodotti PrintService
Estende UnicastRemoteObject per associare un thread agli oggetti che sono istanze di questa classe, 
per fare in modo che il Server non termini l'esecuzione,
*/
public class PrintServiceFactoryImpl extends UnicastRemoteObject implements PrintServiceFactory{
	
	private static final long serialVersionUID = 1L;
	
	public PrintServiceFactoryImpl() throws RemoteException{}
	
	//Il client che invoca il metodo create riceverà un'istanza del proxy, mentre sul server viene creato l'oggetto remoto
	
	public PrintService create() throws RemoteException{
		return new PrintServiceImpl();
	}

}
