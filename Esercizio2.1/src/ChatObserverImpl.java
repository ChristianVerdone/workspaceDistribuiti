import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatObserverImpl extends UnicastRemoteObject implements Observer {
    /**
	 * per rendere l'implementazione accessibile da remoto, la classe estende unicast...
	 */
	private static final long serialVersionUID = 1L;

	protected ChatObserverImpl() throws RemoteException {
    }

    @Override
    public void update(Object msg) throws RemoteException {
        System.out.println("msg = " + msg);
    }
    /*
 		
    public void update() throws RemoteException{
        System.out.println(subj.getState());
    }
    */
}
