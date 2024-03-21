import java.util.concurrent.Future;

public class ActiveObjectTest {

    public static void main(String[] args) throws Exception {
        MathFactory af = new MathActiveObjectFactory();
        Math a = af.create(true);
        
        long startTime = System.currentTimeMillis();
        
        Object results[] = new Object[100];
        for (int i = 0; i < 100; i++) 
            results[i] = a.add(2*i, 3*i); // Riempie l'array
        
        for (int i=0; i<100; i++)
            if (results[i] instanceof Future<?>)
                System.out.println("FwA0: " + ((Future<?>)results[i])); // Fix: Apertura parentesi mancante
            else
                System.out.println("NFwD: "+ results[i] );
        
        // ((Future<Double>)results[0]).get();
        
        
        long elapsed = System.currentTimeMillis() - startTime;
        System.out.println("All calls finished: " + elapsed + " ms ");
        System.out.flush();
    }
}
