import java.rmi.RemoteException;

public class ChatSubjectImpl extends AbstractSubject {
	private static final long serialVersionUID = 1L;
	//private Object state;
	public ChatSubjectImpl() throws RemoteException {
    }
// implementazione tradizionale.
//    public Object getState(){return state;} 
//    public void setState(Object o){state = o; notify();}
}
