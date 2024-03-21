import java.rmi.*;

//Interfaccia che estende Remote e caratterizza un container, imponendo un metodo 
//migrate(Agent agent). 
//un contenitore mette a disposizione un metodo invocabile da remoto per spostare su quel
//contenitore un agente

public interface AgentContainer extends Remote{
	
	public void migrate(Agent agent) throws RemoteException;

}
