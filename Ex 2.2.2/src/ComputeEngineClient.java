import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class ComputeEngineClient {
	public static void main(String[] args) {
		
		
		try {	
			AsyncComputeEngine comp = new AsyncComputeEngineImpl("compute");
			Task t = new AddTask(3,2);
			Future <Object> result = comp.process(t);
			System.out.println("Il risultato del task Add è " + (long)result.get());
			t = new FibTask(6);
			result = comp.process(t);
			System.out.println("Il risultato del task Fibonacci è " + result.get());
								
		} catch (InterruptedException e) {
			System.err.println("The task execution has been interrupted "+ e);	
		} catch (ExecutionException e) {
			System.err.println("An error occured during the task execution "+ e);
		} 
	}
}