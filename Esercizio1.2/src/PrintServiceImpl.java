import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// Definisce la classe "PrintServiceImpl" che estende "UnicastRemoteObject" e implementa "PrintService"
public class PrintServiceImpl extends UnicastRemoteObject implements PrintService {
    private static final long serialVersionUID = 1L;  // Numero di versione per la serializzazione

    // Costruttore protetto che solleva "RemoteException"
    protected PrintServiceImpl() throws RemoteException {
    }

    // Implementazione del metodo "print" con un messaggio di testo
    @Override
    public void print(String msg) throws RemoteException {
        System.out.println("msg = " + msg);  // Stampa il messaggio
    }

    // Implementazione del metodo "print" con un oggetto di tipo "Person"
    @Override
    public void print(Person u) throws RemoteException {
        System.out.println(u);  // Stampa l'oggetto "Person"
    }
}

/*
 * - La classe "PrintServiceImpl" rappresenta un oggetto remoto che implementa l'interfaccia "PrintService".
 * - Estende "UnicastRemoteObject" per supportare la comunicazione remota.
 * - Implementa i metodi "print" per stampare un messaggio di testo o un oggetto "Person".
 * - "RemoteException" è sollevata quando si verificano errori durante l'invocazione di metodi remoti.
 */