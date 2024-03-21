import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ComputeEngine extends Remote{
	public Object process(Task t) throws RemoteException;
}
