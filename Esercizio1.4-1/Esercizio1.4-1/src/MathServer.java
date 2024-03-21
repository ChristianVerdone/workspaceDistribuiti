import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

//Crea un’istanza di MathImpl e lo registra come oggetto remoto al fine di renderlo visibile al client nell’rmiregistry. Il nome scelto è math.

public class MathServer {
	public static void main(String[] args) {
		try {
			Math m = new MathImpl();
			LocateRegistry.createRegistry(1099);
			Naming.bind("rmi://127.0.0.1/math", m);
		}catch(AccessException e){
			System.err.println("Bind operation not permitted");
		}catch(RemoteException e) {
			System.err.println("Registry could not be contacted");
		}catch(MalformedURLException e) {
			System.err.println("Wrong URL for binding");
		}catch(AlreadyBoundException e) {
			System.err.println("Object already bound to the registry");
		}
	}
}