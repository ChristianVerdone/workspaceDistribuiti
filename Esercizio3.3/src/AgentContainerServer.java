import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.Naming;
import java.rmi.RemoteException;


public class AgentContainerServer {
	public static void main(String[] args) {
		try {
			System.setSecurityManager(new SecurityManager());
			//avvia un caontainer con un nome d volta in volta diverso
			AgentContainer ac = new AgentContainerImpl();
			Naming.rebind(("rmi://127.0.0.1/container" + args[0]), ac);
		}  catch (AccessException e) {
			System.err.println("Bind operation not permitted");
		} catch (RemoteException e) {
			System.err.println("Registry could not be contacted");
		} catch (MalformedURLException e) {
			System.err.println("Wrong URL for binding");
//		} catch (AlreadyBoundException e) {
//			System.err.println("Object alreay bound to the registry");
		}
	}
}
