import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ComputeEngineImpl extends UnicastRemoteObject implements ComputeEngine {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ComputeEngineImpl() throws RemoteException {

    }

    public Object process(Task t) throws RemoteException{
        return t.execute();
    }
}
