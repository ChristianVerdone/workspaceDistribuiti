import java.rmi.*;
import java.net.*;
import java.util.*;

/*Recupera il proxy dell’oggetto remoto Math effettuando il lookup sull’rmiregistry. Invoca su di esso il calcolo del fattoriale sincrono, 
 * poi crea un’oggetto callback e lo passa come parametro di getAsynchFactOf per ottenere un risultato in maniera asincrona. 
 * Il ciclo ci permette di visualizzare l’interleaving tra main thread e quello associato al remote object.
 */

public class MathClient {
	public static void main(String[] args) {
		
		Scanner sc = null;
		try {
			//Recupero il proxy dell'oggetto remoto tramite un'operazione di lookup
			Math m = (Math) Naming.lookup("rmi://127.0.0.1/math");
			sc = new Scanner(System.in);
			System.out.println("Inserisci il numero di cui vuoi il fattoriale");
			int v = sc.nextInt();
			
			//Synchronous Invocation
			//Il client aspetta il risultato prima di eseguire l'istruzione successiva
			int f = m.getFactOf(v);
			System.out.println("Il fattoriale di " + v + " è " + f);
			
			//Asynchronous Invocation
			//Creo un'instanza concreta di callback, quindi sto creando un oggetto remoto che viene impiantato nel client
			CallBack cb = new CallBackImpl();
			/*in questo caso il metodo è void, non ha un tipo di ritorno, il valore di ritorno sarà restituito in modo differito invocando 
			 * la callback che sto passando
			 */
			m.getAsyncFactOf(v, cb);
			/*dopo aver invocato questo metodo il client potrà continuare ad eseguire del codice mentre il fattoriale viene prodotto, 
			 * quindi mentre stampa questa stringa a video arriva il risultato
			 */
			for(int i = 0; i < 80; i++) 
				System.out.println("Ti dimostro che l'invocazione è asincrona");
			
			}catch(RemoteException e) {
				System.err.println(e);
			}catch(MalformedURLException e) {
				System.err.println("Wrong URL for binding");
			}catch(NotBoundException e) {
				System.err.println("Object not found");
			}finally {
				if(sc != null) sc.close();
		}
	}
}
