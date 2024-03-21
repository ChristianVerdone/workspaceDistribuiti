import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.net.*;

//Per ogni nodo si avvia esplicitamente un server, passandogli come argomento il suo 
//id; crea un'instanza di  AgentContainer e lo registra per renderlo accessibile

//Per eseguire bisogna andare in runConfigurations e mettere un indice come argomento

public class AgentContainerServer {
	public static void main(String[] args) {
		try {
			AgentContainer ac = new AgentContainerImpl();
			
			//PER AVVIARE RECARSI IN RUN CONFIGURATIONS E SETTARE IL PARAMETRO
			//LA PRIMA VOLTA BISOGNA CREARE L'RMI REGISTRY
			//LocateRegistry.createRegistry(1099);
			//ogni volta che viene avviato un server deve essere specificato come parametro 
			//sulla linea di comando un intero al fine di avere nomi diversi per i container
			//registra il container nel registro
			Naming.rebind("rmi://127.0.0.1/container" + args[0], ac);
			System.out.println("Container registered");
		}catch(AccessException e) {
			System.err.println("Bind operation not permitted");
		}catch(RemoteException e) {
			System.err.println("Registry could not be contacted");
		}catch(MalformedURLException e) {
			System.err.println("Wrong URL for binding");
		}
	}
}
