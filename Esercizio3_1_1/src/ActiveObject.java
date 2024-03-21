import java.lang.reflect.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ActiveObject implements InvocationHandler {
	private Object servant;
	private ExecutorService scheduler;
	
	public ActiveObject(Object servant) {
		this.servant = servant;
		// Creates an Executor that uses a single worker thread operating off an unbounded queue.
		// garanzia dell'esecuzione sequenziale dei task
		this.scheduler = Executors.newSingleThreadExecutor();
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//Call call = new Call(method, args);
		
		// submit ritorna Future<Object>
		// creo un oggetto (Callable<Object> task) e implemento il metodo call
		return scheduler.submit(()->method.invoke(servant, args));
	}
	
	//private static class Call {
		//public Call(Method m, Object[] a) {
			//method = m; args = a;
		//}
		//public Method method;
		//public Object[] args;
	//}
	
}
