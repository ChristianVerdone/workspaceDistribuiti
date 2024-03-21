import java.rmi.*;
import java.io.Serializable;
import java.net.*;

// Definizione di un'eccezione personalizzata per gestire gli errori relativi al collegamento dei nodi
class NodeLinkingException extends Exception {
    private static final long serialVersionUID = 1L;
}

// La classe Node rappresenta un riferimento a un contenitore di agenti
// È caratterizzata da un ID
public class Node implements Serializable {
    private static final long serialVersionUID = 1L;
    private AgentContainer container; // Contenitore di agenti associato al nodo

    // Costruttore della classe Node
    public Node(int index) throws NodeLinkingException {
        try {
            // Cerca il contenitore di agenti utilizzando RMI e l'indice specificato
            container = (AgentContainer) Naming.lookup("rmi://127.0.0.1/container" + index);
            System.out.println(container); // Stampa il contenitore trovato (a scopo di debug)
        } catch (RemoteException e) {
            // Gestione dell'eccezione se non è possibile contattare il registro RMI
            System.err.println("Impossibile contattare il registro RMI");
            throw new NodeLinkingException();
        } catch (MalformedURLException e) {
            // Gestione dell'eccezione se l'URL per il binding è errato
            System.err.println("URL errato per il binding");
            throw new NodeLinkingException();
        } catch (NotBoundException e) {
            // Gestione dell'eccezione se l'oggetto non è registrato
            System.err.println("Oggetto non registrato");
            throw new NodeLinkingException();
        }
    }

    // Metodo per migrare un agente al nodo corrente
    public void migrate(Agent agent) throws RemoteException {
        container.migrate(agent); // Chiama il metodo migrate() del contenitore di agenti
    }
}