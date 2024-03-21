import java.util.concurrent.Future;

public class ActiveObjectTest {

	public static void main(String[] args) throws Exception {
		MathFactory af = new MathActiveObjectFactory();
		Math a = af.create(true);
		
		long startTime = System.currentTimeMillis();
		
		Object results[] = new Object[100];
		for (int i = 0; i < 100; i++) {
			results[i] = a.add(2*i, 3*i);  // riempie l'array
		}
		
		for (int i = 0; i < 100; i++) {
			if(results[i] instanceof Future<?>)
				System.out.println("FwAO: " + ((Future<?>)results[i]).get());  // stampa l'array
			else
				System.out.println("NFwO: " + results[i]);  // stampa l'array
		}
		// ((Future<Double>)results[0]).get();
		long elapsed = System.currentTimeMillis() - startTime;
		System.out.println("All calls finished: " + elapsed + " ms ");
		System.out.flush();
		
		
	}

}
