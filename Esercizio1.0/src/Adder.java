import java.rmi.*;

public interface Adder extends Remote {
    public long add(int op1, int op2) throws RemoteException; //all'atto di invocazione può esserci errore
}
