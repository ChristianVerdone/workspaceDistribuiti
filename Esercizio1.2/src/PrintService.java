import java.rmi.Remote;
import java.rmi.RemoteException;

// Definisce l'interfaccia "PrintService" che estende "Remote"
public interface PrintService extends Remote {
    // Dichiarazione del metodo di stampa con un messaggio di testo
    public void print(String msg) throws RemoteException;

    // Dichiarazione del metodo di stampa con un oggetto di tipo "Person"
    public void print(Person u) throws RemoteException;
}

/*
// - L'interfaccia "PrintService" espone due metodi di stampa:
//   1. "print(String msg)": accetta un messaggio di testo e lo stampa.
//   2. "print(Person u)": accetta un oggetto di tipo "Person" e lo stampa.
// - Entrambi i metodi possono essere chiamati da oggetti remoti tramite RMI (Remote Method Invocation).
// - "RemoteException" è sollevata quando si verificano errori durante l'invocazione di metodi remoti.
*/
