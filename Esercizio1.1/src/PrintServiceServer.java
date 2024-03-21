import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;

/*Crea un’istanza di PrintServiceImpl e lo registra come oggetto remoto al fine di renderlo 
visibile al client nell’rmiregistry. Il nome scelto è printservice.
*/
public class PrintServiceServer {
	public static void main(String[] args) {
		try {
			PrintService ps = new PrintServiceImpl();
			
			//Creiamo un registro in ascolto sulla porta 1099 prima dell'operazione di bind
			LocateRegistry.createRegistry(1099);
			
			/*Si tratta di operazioni remote, trasferiamo al registro il proxy dell'oggetto remoto
			viene passato come parametro il proxy dell'oggetto remoto, cioè il riferimento dell'oggetto remoto
			Questa operazione trasferisce il proxy nel registro e nel registro troveremo associato al nome printService
			il riferimento al proxy che sarà recuperato dal client tramite l'operazione di lookup
			*/
			Naming.bind("rmi://127.0.0.1/printservice", ps);
			
		}catch(AccessException e){
			System.err.println("Bind operation not permitted");
		}catch(RemoteException e) {
			System.err.println("Registry could not be contacted");
		}catch(MalformedURLException e) {
			System.err.println("Wrong URL for binding");
		}catch(AlreadyBoundException e) {
			System.err.println("Object already bound to the registry");
		}
	}

}
