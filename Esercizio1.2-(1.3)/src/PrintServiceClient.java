import java.rmi.*;
import java.net.*;
import java.util.*;

/*Recupera il proxy dell’oggetto remoto PrintServiceFactory effettuando il lookup 
sull’rmiregistry. Invoca su di esso il metodo di creazione, che verrà eseguita su 
server. L’oggetto PrintService restituito può essere usato per stampare dei 
messaggi.
*/
public class PrintServiceClient {
	public static void main(String[] args) {
		Scanner sc = null;
		try {
			sc = new Scanner(System.in);
			String s = null;
			//Lookup per recuperare la factory
			PrintServiceFactory psf = (PrintServiceFactory) Naming.lookup("rmi://127.0.0.1/printservicefactory");
			/*
			Quando creiamo un oggetto scriveremo PrintService nome variabile =
			Facciamo riferimento alla factory concreta che vogliamo utilizzare per la creazione
			e create ci consentirà di creare l'istanza del tipo che vogliamo produrre
			il problema della creazione si sposta sull'invocazione del metodo create e il 
			polimorfismo che possiamo utilizzare sul metodo create ci consente di creare un oggetto
			istanza di una classe piuttosto che di un'altra classe in maniera trasparente
			
			Effettua la creazione di un oggetto tramite il metodo remoto create, viene creata un'istanza di tipo
			PrintService sul server (istanza della classe printserviceimpl). Questa istanza è un nuovo oggetto 
			remoto il cui proxy viene recuperato attraverso la variabile ps. 
			Quindi ps referenzierà il proxy dell'oggetto appena creato
			*/
			PrintService ps = psf.create();
			do {
				s = sc.nextLine();
				//quando invochiamo print su ps viene invocata sull'oggetto remoto appena creato
				ps.print(s);
			}while(!s.equals("."));
			ps.print("test");
		}catch(RemoteException e) {
			System.err.println("Remote invocation error" + e);
		}catch(MalformedURLException e) {
			System.err.println("Wrong URL for binding");
		}catch(NotBoundException e) {
			System.err.println("Object not found");
		}finally {
	         // Chiusura dello scanner per liberare le risorse
	         if (sc != null) sc.close();
	    }
	}
}
