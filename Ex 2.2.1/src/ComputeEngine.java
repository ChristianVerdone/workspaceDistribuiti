import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.concurrent.Future;

public interface ComputeEngine extends Remote{
	public Future <Object> process(Task t) throws RemoteException;
}
