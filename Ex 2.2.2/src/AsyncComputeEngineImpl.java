import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AsyncComputeEngineImpl implements AsyncComputeEngine {

    private String name;

    public AsyncComputeEngineImpl(String name) {
        this.name = name;
    }
// Future<Object> non sono serializzabili
// ogni volta che chiama process avvia un thread(excutor) e si ha la chiamata asincrona   
    @Override
    public Future<Object> process(Task t) {
        try {
            // Effettua il lookup dell'oggetto remoto di tipo ComputeEngine utilizzando l'indirizzo RMI specificato
            ComputeEngine comp = (ComputeEngine) Naming.lookup("rmi://127.0.0.1/" + name);

            // Crea un executor con un singolo thread
            ExecutorService executor = Executors.newSingleThreadExecutor(); 
            //L'ExecutorService è responsabile per la gestione del pool di thread e 
            //l'esecuzione delle attività sottomesse ad esso

            // Crea una callable (un'istanza di Runnable che restituisce un risultato)
            
//            Callable<Object> task = new Callable<Object>() {
//                @Override
//                public Object call() throws Exception {
//                    return comp.process(t);
//                }
//            };

            
            Callable<Object> task = () -> comp.process(t);

            // Sottomette il task all'executor e ottiene un oggetto Future che rappresenta il risultato del calcolo
            return executor.submit(task);
        } catch (MalformedURLException e) {
            System.err.println("URL di binding errato: " + e);
        } catch (RemoteException e) {
            System.err.println("Errore di invocazione remota: " + e);
        } catch (NotBoundException e) {
            System.err.println("Oggetto non associato: " + e);
        }
        return null; // Restituisce null in caso di problemi
    }
}
