import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;

//Crea un’istanza di PrintServiceFactory e lo registra come oggetto remoto al fine di 
//renderlo visibile al client nell’rmiregistry. Il nome scelto è printservicefactory.

public class PrintServiceFactoryServer {
	public static void main(String[] args) {
		try {
			//Creiamo un'instanza della factory
			PrintServiceFactory psf = new PrintServiceFactoryImpl();
			//Creiamo un registro in ascolto sulla porta 1099 prima dell'operazione di bind
			LocateRegistry.createRegistry(1099);
			//Registriamo la factory, quindi il client può recuperare la factory ed istanziare gli oggetti 
			Naming.bind("rmi://127.0.0.1/printservicefactory", psf);
		}catch(AccessException e){
			System.err.println("Bind operation not permitted");
		}catch(RemoteException e) {
			System.err.println("Remote invocation error " + e);
		}catch(MalformedURLException e) {
			System.err.println("Wrong URL for binding");
		}catch(AlreadyBoundException e) {
			System.err.println("Object already bound to the registry");
		}
	}
}
