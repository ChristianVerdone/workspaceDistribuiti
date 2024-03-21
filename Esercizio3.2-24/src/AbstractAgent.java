import java.rmi.RemoteException;

public abstract class AbstractAgent implements Agent {
	private static final long serialVersionUID = 1L;
	private transient Thread myThread; //transient significa che il campo non sarà serializzato

	public void migrateTo(Node node) throws RemoteException {
		node.migrate(this);
	}

	public void start() {
		myThread = new Thread(this);
		myThread.start();
	}

}
