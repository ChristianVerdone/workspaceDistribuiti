import java.io.Serializable;
import java.rmi.RemoteException;

public interface Node extends Serializable{
	public void migrate(Agent agent) throws RemoteException;
}
