import java.lang.reflect.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ActiveObject implements InvocationHandler {
    private Object servant; // Il "servant" è l'oggetto reale su cui verranno chiamati i metodi in modo asincrono.
    private ExecutorService scheduler; // Scheduler per la gestione asincrona dei metodi.

    // Costruttore che accetta l'oggetto reale da utilizzare come "servant".
    public ActiveObject(Object servant) {
        this.servant = servant;
        // Crea un Executor che utilizza un singolo thread worker che opera su una coda senza limiti.
        // Questo garantisce l'esecuzione sequenziale dei task.
        this.scheduler = Executors.newSingleThreadExecutor();
    }
    
    // Questo metodo viene chiamato ogni volta che viene invocato un metodo sull'oggetto proxy associato.
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // Sottomette l'esecuzione del metodo al scheduler.
        // Il metodo viene eseguito in modo asincrono, restituendo un Future che rappresenta il risultato.
        return scheduler.submit(() -> method.invoke(servant, args));
    }
   
 // questa classe implementa il pattern Active Object, 
 //che permette di eseguire chiamate a metodi in modo asincrono, senza bloccare il thread chiamante.
 //La classe ActiveObject utilizza riflessione per intercettare le chiamate ai metodi e le sottomette a un executor per l'esecuzione asincrona.
}