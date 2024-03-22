import java.lang.reflect.*;

//è utilizzato come handler di invocazione per un proxy e aggiunge funzionalità di logging alla chiamata dei metodi sull'oggetto reale. 
//Quando un metodo viene invocato sul proxy, il metodo invoke viene chiamato e stampa un messaggio di debug

class LoggerHandler implements InvocationHandler { 
    private Object realSubject;

    //costruttore che ha come parametro l'oggetto che rappresenta l'oggetto reale su cui verranno inoltrate le chiamate
    public LoggerHandler(Object s) {
        realSubject = s;
    }

   
    public Object invoke(Object proxy, Method m, Object[] args) throws Throwable {
     
        System.out.println("Debug: You have invoked method: " + m);
        try {
            
            return m.invoke(realSubject, args); //viene chiamato il metodo reale m
        } catch (InvocationTargetException e) {  //gestire eventuali eccezioni sollevate durante l'invocazione del metodo reale
            throw e.getCause();
        }
    }
}

