// Importa le librerie Java necessarie
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

// Definisce un'interfaccia chiamata DBSearcher con un unico metodo chiamato "search"
interface DBSearcher {
    String search(String target);
}

// Crea una classe chiamata DBSearcherImpl che implementa l'interfaccia DBSearcher
class DBSearcherImpl implements DBSearcher {
    public String search(String key) {
        return key + " cercato";  // Restituisce la chiave di input concatenata con " cercato"
    }
}

// Definisce una classe pubblica chiamata FutureExample
public class FutureExample {
    private static ExecutorService executor = Executors.newSingleThreadExecutor();  // Crea un executor a singolo thread
    private static DBSearcher searcher = new DBSearcherImpl();  // Crea un'istanza di DBSearcherImpl

    // Definisce un metodo statico chiamato "showSearch" che prende in input una chiave di tipo String e solleva InterruptedException
    public static void showSearch(String key) throws InterruptedException {
        Callable<String> task = () -> searcher.search(key);  // Crea un compito Callable che cerca nel database
        Future<String> future = executor.submit(task);  // Invia il compito all'executor e ottieni un risultato Future

        System.out.println("altro");  // Stampa "altro" (rappresenta altre attività eseguite contemporaneamente alla ricerca)

        try {
            System.out.println(future.get());  // Ottieni il risultato dal Future (chiamata bloccante)
            executor.shutdown();  // Arresta l'executor
        } catch (ExecutionException ex) {
            return;  // Gestisci eventuali eccezioni durante l'esecuzione
        }
    }

    // Definisce il metodo main
    public static void main(String[] args) throws InterruptedException {
        showSearch("Christian");  // Chiama il metodo showSearch con la chiave di input "Eugenio"
    }
}

// Nel complesso, questo codice Java dimostra come utilizzare un executor a singolo thread, inviare un compito Callable e recuperare il risultato tramite un Future.
// Il segnaposto "altro" rappresenta altre attività che possono essere eseguite contemporaneamente mentre si attende il risultato della ricerca.
// L'handling delle eccezioni è minimo in questo esempio.
