import java.rmi.*;
import java.rmi.server.*;

//Estende UnicastRemoteObject (è un oggetto remoto) che implementa 
//AgentContainer e si occupa di avviare l’esecuzione dell’agente ricevuto.

public class AgentContainerImpl extends UnicastRemoteObject implements AgentContainer{
	
	private static final long serialVersionUID = 1L;
	
	public AgentContainerImpl() throws RemoteException{}
	
	//l'agente che viene recuperato da remoto viene semplicemente avviato
	//il codice specifico dell'applicazione si troverà nell'agente
	//quindi all'invocazione di start l'agente inizierà ad eseguire utilizzando il thread associato
	//ovvero esegue le istruzioni presenti nel metodo run dell'agent
	public void migrate(Agent agent) throws RemoteException{
		if(agent != null) agent.start();
	}

}
