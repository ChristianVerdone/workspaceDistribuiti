import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Math extends Remote {
    public int getFibonacciOf(int i ) throws RemoteException;
    public int getAsyncFibonacciOf(int i, CallBack continuation) throws RemoteException;
}
