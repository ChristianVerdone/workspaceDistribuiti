import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AgentContainer extends Remote {
	void migrate(Agent agent) throws RemoteException;
}
