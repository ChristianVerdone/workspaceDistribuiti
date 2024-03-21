import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Subject extends Remote {

    public void attach(Observer o) throws RemoteException;
    public void detach(Observer o) throws RemoteException;

    public void notify(Object msg) throws RemoteException;

    /*
        elementi dell pattern observer
     	notify sensa parametro esiste in object e serve per gestire la sincronizzazione perciò cambiamo il nome
    	public void notifyS() throws RemoteException;
     */
}
