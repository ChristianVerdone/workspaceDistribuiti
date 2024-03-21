import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.LinkedBlockingQueue;
/*
 * un oggetto passivo generico può essere decorato con un thread di controllo e una coda per serializzare gli accessi 
 * usiamo questa classe per specificare il comportamento del proxy 
 */
public class ActiveObject implements InvocationHandler {

	// usata per accodare le chiamate
    private LinkedBlockingQueue<Call> activationQueue = new LinkedBlockingQueue<Call>();
    // implementazione concreta del servant
    private Object servant;
    // scheduler per gestire le chiamate
    private Thread scheduler;
    
    public static final ThreadGroup schedulers = new ThreadGroup("ActiveObjectSchedulers");

    public ActiveObject(Object servant) {
        // rendiamo attivo l'oggetto passivo passato come parametro al costruttore
    	this.servant = servant;
        //inizializzo lo scheduler
        this.scheduler = new Scheduler();
        this.scheduler.start();
        // la coda si potrebbe creare anche qui.
    }

    // Mette in coda una chiamata da eseguire
    private void enqueue(Call call) {
        activationQueue.add(call);
    }

    // Estrae e restituisce una chiamata dalla coda (bloccante se la coda è vuota)
    private Call dequeue() throws InterruptedException {
        return activationQueue.take();
    }

    // Questo metodo viene chiamato quando si invoca un metodo sull'Active Object, proviene dall'invocation handler
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    	//viene creato l'oggetto call
        Call call = new Call(method, args); //add e argomenti dell'add
        enqueue(call);
        //l'add sulla coda può essere fatto anche direttamente qui
        return null; 
    }

    // Le chiamate vengono estratte dallo scheduler e viene utilizzata la reflection per effettuare
    // l'invocazione del metodo sul servant
    private class Scheduler extends Thread {

        public Scheduler() {
            super(schedulers, "Scheduler");
        }

        public void run() {
        	boolean isInterrupted = false;
        	//in esecuzione fin quando non sia interrotto e la coda sia vuota
            while (!(isInterrupted && activationQueue.size() == 0)) {
                try {
                	// prendo una chiamata dalla coda e la eseguo all'interno del thread
                    Call call = dequeue();
                    dispatch(call);
                }catch (InterruptedException e) {
                    isInterrupted = true;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            System.out.println("Thread " + this + " is terminating " + activationQueue.size());
        }

        // Esegue l'effettiva chiamata al metodo sul "servant" (oggetto target) usando la reflection
        private void dispatch(Call call) throws IllegalAccessException, InvocationTargetException, IllegalArgumentException {
        	// viene estratto il metodo da call e viene invocato su servant passando gli argomenti
            call.method.invoke(servant, call.args);
        }
    }

    // Classe interna per rappresentare una chiamata
    private static class Call {
        public Call(Method m, Object[] a) {
            method = m;
            args = a;
        }

        public Method method;
        public Object[] args;
    }
}
