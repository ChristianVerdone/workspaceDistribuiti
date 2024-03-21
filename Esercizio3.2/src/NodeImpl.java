import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

class NodeLinkingException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}

public class NodeImpl implements Node {

	private static final long serialVersionUID = 1L;
	public AgentContainer container;

	public NodeImpl(int index) throws NodeLinkingException {
		try {
			container = (AgentContainer) Naming.lookup("rmi://127.0.0.1/container" + index);
			System.out.println("container = " + container);
		} catch (MalformedURLException e) {
//			System.err.println("Wrong URL for binding");
			throw new NodeLinkingException();
		} catch (RemoteException e) {
//			System.err.println("Registry could not be contacted");
			throw new NodeLinkingException();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			throw new NodeLinkingException();
		}
	}

	public void migrate(Agent agent) throws RemoteException {
		container.migrate(agent);
	}

}
