import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CallBack extends Remote {
    public void setValue(int v) throws RemoteException;
}
