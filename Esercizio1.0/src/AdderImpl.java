import java.rmi.server.*;
import java.rmi.*;

class AdderImpl extends UnicastRemoteObject implements Adder { 
	/**capiremo perchè si estende unicast all'esecuzione
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    public AdderImpl() throws RemoteException { }   
    public long add(int op1, int op2) throws RemoteException {    
        return op1 + op2;   
     }
}

