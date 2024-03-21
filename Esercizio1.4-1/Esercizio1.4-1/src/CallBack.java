import java.rmi.*;

//Interfaccia che estende remote ed impone il metodo setValue(int fact). il setValue va a restituire il risultato al chiamante (valore intero)
//si tratta di un'interfaccia che estende remote, quindi una callBack concreta sarà instanziabile come oggetto remoto
public interface CallBack extends Remote{
	public void setValue(int v) throws RemoteException;
}
