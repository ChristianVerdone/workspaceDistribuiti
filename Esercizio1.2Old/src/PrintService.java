import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PrintService extends Remote {

    public void print(String msg) throws RemoteException;
}
