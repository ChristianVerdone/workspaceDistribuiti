import java.rmi.*;
import java.net.*;
import java.util.*;

//Recupera il proxy dell’oggetto remoto effettuando il lookup sull’rmiregistry. Riceve 
//un flusso di caratteri dallo stdInput e stampa finchè non trova il punto.

public class PrintServiceClient {
	public static void main(String[] args) {
		 Scanner sc = null;
		try {
			sc = new Scanner(System.in);
			String s = null;
			//ps è il reference all'oggetto remoto, viene convertito in printservice in modo
			//da poter invocare previsti dall'interfaccia printService
			PrintService ps = (PrintService) Naming.lookup("rmi://127.0.0.1/printservice");
			do {
				s = sc.nextLine();
				ps.print(s);
			}while(!s.equals("."));
		}catch(RemoteException e) {
			System.err.println("Registry could not be contacted" + e);
		}catch(MalformedURLException e) {
			System.err.println("Wrong URL for binding");
		}catch(NotBoundException e) {
			System.err.println("Object not bound");
		}finally {
	         // Chiusura dello scanner per liberare le risorse
	         if (sc != null) sc.close();
	    }
	}
}