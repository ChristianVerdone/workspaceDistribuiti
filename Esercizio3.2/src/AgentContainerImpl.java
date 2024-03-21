import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class AgentContainerImpl extends UnicastRemoteObject implements AgentContainer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AgentContainerImpl() throws RemoteException {}

	@Override
	public void migrate(Agent agent) throws RemoteException {
		// TODO Auto-generated method stub
		if(agent != null) agent.start();
	}

}
