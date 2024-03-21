import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CallBackImpl extends UnicastRemoteObject implements CallBack {
    private static final long serialVersionUID = 1L;

	protected CallBackImpl() throws RemoteException {
    }

    @Override
    public void setValue(int v) throws RemoteException {
        System.out.println("Il valore restituito dalla callback è = " + v);
    }
}
