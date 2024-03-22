import java.util.concurrent.Future;

public class ActiveObjectTest {

    public static void main(String[] args) throws Exception {
        // Creazione di un'istanza di MathFactory che restituirà un Active Object per le operazioni matematiche.
        MathFactory af = new MathActiveObjectFactory();
        // Creazione di un'istanza di Math tramite la MathFactory.
        Math a = af.create(true);
        
        // Memorizza il tempo di inizio dell'esecuzione.
        long startTime = System.currentTimeMillis();
        
        // Array per memorizzare i risultati delle chiamate ai metodi.
        Object results[] = new Object[100];
        // Effettua 100 chiamate asincrone al metodo "add" dell'oggetto Math.
        for (int i = 0; i < 100; i++) 
            results[i] = a.add(2*i, 3*i); 
        
        // Stampa i risultati delle chiamate.
        for (int i=0; i<100; i++)
            // Verifica se il risultato è un Future (indicante un'operazione asincrona).
            if (results[i] instanceof Future<?>)
                // Se il risultato è un Future, stampa la rappresentazione testuale del Future.
                System.out.println("FwA0: " + ((Future<?>)results[i])); 
            else
                // Se il risultato non è un Future, stampa direttamente il risultato.
                System.out.println("NFwD: "+ results[i] );
        
        // Calcola il tempo trascorso dall'inizio dell'esecuzione.
        long elapsed = System.currentTimeMillis() - startTime;
        // Stampa il tempo trascorso.
        System.out.println("All calls finished: " + elapsed + " ms ");
        System.out.flush();
    }
}