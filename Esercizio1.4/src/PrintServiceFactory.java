import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PrintServiceFactory extends Remote {
    public PrintService create() throws RemoteException;

}
