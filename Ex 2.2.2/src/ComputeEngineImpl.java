import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ComputeEngineImpl extends UnicastRemoteObject implements ComputeEngine{

	protected ComputeEngineImpl() throws RemoteException {}

	private static final long serialVersionUID = 1L;

	@Override
	public Object process(Task t) throws RemoteException {
		try {
			return t.call();
		} catch (Exception e) {
			return null;
		}
	}

}
