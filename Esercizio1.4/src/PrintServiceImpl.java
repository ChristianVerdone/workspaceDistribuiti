import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PrintServiceImpl extends UnicastRemoteObject implements PrintService {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected PrintServiceImpl() throws RemoteException {
    }


    @Override
    public void print(String msg) throws RemoteException {
        System.out.println("msg = " + msg);
    }

    @Override
    public void print(User u) throws RemoteException {
        System.out.println("u = " + u);
    }
    public void unreferenced(){
        System.out.println("Finalizing");
    }
}
