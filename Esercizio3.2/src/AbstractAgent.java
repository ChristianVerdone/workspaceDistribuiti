import java.rmi.*;

//Implementa l�interfaccia Agent e fattorizza il codice di funzionamento basilare per 
//gli agenti concreti, come il metodo di avvio e di migrazione. 
//Nel primo avvia il thread 
//di controllo, nel secondo effettua la migrazione verso un Nodo. Il thread di controllo 
//� transiente, cio� non � serializzabile dato che verr� ricreato nel successivo 
//container. 

public abstract class AbstractAgent implements Agent{
	
	private static final long serialVersionUID = 1L;
	
	//Thread che viene associato all'agente, transient indica che la variabile non viene serializzata
	//dato che i thread per definizione non lo sono, dato che fanno riferimento a registri significativi 
	//localmente, il contesto di esecuzione del thread non pu� essere trasferito
	private transient Thread myThread;
	
	//Riceve un nodo come parametro e invoca migrate sul nodo, quindi il problema si sposta sull'interfaccia del nodo
	public void migrateTo(Node node) throws RemoteException{
		node.migrate(this);
	}
	
	//viene creato il Thread e avviato, poich� su ogni contenitore deve esserci un Thread specifico
	public void start() {
		myThread = new Thread(this); //gli passiamo il metodo run
		myThread.start();
	}
}
