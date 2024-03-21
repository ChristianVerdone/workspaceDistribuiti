import java.rmi.*;
import java.rmi.server.*;

//È un’oggetto remoto che estende UnicastRemoteObject e implementa CallBack. Stampa il valore del fattoriale calcolato in maniera asincrona.

public class CallBackImpl extends UnicastRemoteObject implements CallBack{
	
	private static final long serialVersionUID = 1L;
	public CallBackImpl() throws RemoteException{}
	
	public void setValue(int r) throws RemoteException{
		System.out.println("Il valore restituito dalla CallBack è " + r);
	}
}
