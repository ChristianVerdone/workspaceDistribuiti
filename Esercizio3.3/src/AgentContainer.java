import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AgentContainer extends Remote {
	//sposta l'agent nel container specifico
	void migrate(Agent agent) throws RemoteException;
}

