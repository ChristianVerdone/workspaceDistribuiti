import java.io.Serializable;
import java.rmi.RemoteException;

public interface Agent extends Serializable, Runnable {
	// Agent body: the code in this method will be executed whenever the agent reaches a node
	void run();

	// Starts the execution of the agent code in a new thread
	void start();

	// Migrates the agent that invokes this method to the specified node
	void migrateTo(Node node) throws RemoteException;
}