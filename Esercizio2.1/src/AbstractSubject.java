import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSubject extends UnicastRemoteObject implements Subject {
	private static final long serialVersionUID = 1L;
	private List<Observer> obs = new ArrayList<>();  // Crea una lista di osservatori

	// Costruttore protetto che solleva RemoteException
	protected AbstractSubject() throws RemoteException {
	}

	// Implementazione del metodo "attach" dell'interfaccia Subject
	@Override
	public void attach(Observer o) throws RemoteException {
	   obs.add(o);  // Aggiunge un osservatore alla lista
	}

	// Implementazione del metodo "detach" dell'interfaccia Subject
	@Override
	public void detach(Observer o) throws RemoteException {
	   obs.remove(o);  // Rimuove un osservatore dalla lista
	}

	// Implementazione del metodo "notify" dell'interfaccia Subject
	@Override
	public void notify(Object msg) throws RemoteException {
	   for (Observer observer : obs) {
	       observer.update(msg);  // Notifica tutti gli osservatori con un messaggio specifico
	       //con l'altro approccio uso update()
	   }
	}

/*
    @Override
    public void notifyS() throws RemoteException {
        for(Observer observer : obs){
            observer.update();
        }
    }
    */
}