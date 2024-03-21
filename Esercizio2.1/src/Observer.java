import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Observer extends Remote {

    void update(Object msg) throws RemoteException;

//    to get the content
//    void update() throws RemoteException;
}
