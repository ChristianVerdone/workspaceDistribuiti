import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class ComputeEngineClient {
	public static void main(String[] args) {
		
		
		try {	
			ComputeEngine comp = (ComputeEngine)Naming.lookup("rmi://127.0.0.1/compute");
			Task t = new AddTask(3,2);
			// Esegue il Task di somma asincronamente 
			Future <Object> result = comp.process(t);
			System.out.println("Il risultato del task è " + (long)result.get());
			//t = new FibTask(6);
			//result = (int) comp.process(t);
			//System.out.println("Il risultato del task è " + result);
		
								
		} catch (RemoteException e) {
			System.err.println("Remote Invocation Error" + e);
		} catch (MalformedURLException e) {
			System.err.println("Wrong URL for binding" + e);
		} catch (NotBoundException e) {
			System.err.println("Object not bound "+ e);	
		} catch (InterruptedException e) {
			System.err.println("The task execution has been interrupted "+ e);	
		} catch (ExecutionException e) {
			System.err.println("An error occured during the task execution "+ e);
		} 
	}
}