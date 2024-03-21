import java.lang.reflect.*;

// Classe che implementa l'handler di invocazione per il proxy
class LoggerHandler implements InvocationHandler {
    private Object realSubject;

    // Costruttore che accetta l'oggetto reale da intercettare
    public LoggerHandler(Object s) {
        realSubject = s;
    }

    // Metodo di invocazione richiesto dall'interfaccia InvocationHandler
    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
        /* Aggiungi un messaggio di log per il metodo chiamato, un metodo generale per rendere l'implementazione unica 
         * per ogni metodo
         */
        System.out.println("Debug: You have invoked method: " + m);
        try {
            /* Inoltra la chiamata al metodo sull'oggetto reale e restituisci il risultato
             * in invoke indichiamo l'oggetto sul quale invocare il metodo e i parametri da passare.
             */
            return m.invoke(realSubject, args);
        } catch (InvocationTargetException e) {
            // Se il metodo reale genera un'eccezione, rilancia l'eccezione originale
            throw e.getCause();
        }
    }
}

// per definire il comportamento specifico di un proxy dobbiamo implementare invoke
// Object[] sono gli argomenti del Method
// Il metodo che viene invocato viene trasformato in un oggetto

// invoke funziona per ogni metodo

//il logging potrebbe essere usato da altre classi
// scriviamo un handler che definisce il comportamento di un proxy
// l'handler è usato dall'interfaccia Math ma è possibile utilizzarlo anche per altre interfacce 
// possiamo definire un handler per fare security, un handler per fare profiling ecc.

// proxy parte comportamentale