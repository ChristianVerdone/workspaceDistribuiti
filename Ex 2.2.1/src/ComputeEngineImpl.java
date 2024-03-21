import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ComputeEngineImpl extends UnicastRemoteObject implements ComputeEngine{

	private static final long serialVersionUID = 1L;
	
	protected ComputeEngineImpl() throws RemoteException {}

	@Override
	public Future <Object> process(Task t) throws RemoteException {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		try {
			return executor.submit(t);
		} catch (Exception e) {
			return null;
		}
	}

}
