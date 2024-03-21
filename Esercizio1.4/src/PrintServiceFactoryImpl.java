import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PrintServiceFactoryImpl extends UnicastRemoteObject implements PrintServiceFactory {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected PrintServiceFactoryImpl() throws RemoteException {
    }

    @Override
    public PrintService create() throws RemoteException {
        return new PrintServiceImpl();
    }
}
