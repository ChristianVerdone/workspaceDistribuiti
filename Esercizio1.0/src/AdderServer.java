import java.rmi.*;
import java.net.*;


public class AdderServer {
	public static void main(String args[])  {
		try {
			// Creazione di un'istanza dell'oggetto AdderImpl che implementa l'interfaccia Adder
			Adder a = new AdderImpl(); // Oggetto ospitato in remoto
			// Registrazione dell'oggetto Adder nel registro RMI locale all'indirizzo 127.0.0.1
			Naming.bind("rmi://127.0.0.1/adder", a); // Registrazione in remoto. Vari casi eccezionali per situazioni anomale
		} catch (AccessException e) {
			// Eccezione lanciata se l'accesso al registro è vietato
			System.err.println("Bind operation not permitted");
		} catch (RemoteException e) {
			// Eccezione lanciata se non è possibile contattare il registro RMI
			System.err.println("Registry could not be contacted");
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// Eccezione lanciata se l'URL fornito per la registrazione è errato
			System.err.println("Wrong URL for binding");
		} catch (AlreadyBoundException e) {
			// Eccezione lanciata se l'oggetto è già registrato con lo stesso nome nel registro RMI
			System.err.println("Object already bound to the registry");
		}
	}
}
