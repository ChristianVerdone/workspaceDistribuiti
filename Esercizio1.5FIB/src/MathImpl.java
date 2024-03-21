
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MathImpl extends UnicastRemoteObject implements Math {
    private static final long serialVersionUID = 1L;


	protected MathImpl() throws RemoteException {
    }

    private int fib(int n){
        if((n==0) || (n==1)) return 1;
        else return fib(n-1) + fib(n-2);
    }
    @Override
    public int getFibonacciOf(int i) throws RemoteException {
        return fib(i);
    }

    @Override
    public int getAsyncFibonacciOf(int i, CallBack continuation) throws RemoteException{
        new AsyncFibonacci(i, continuation).start();
        return i;
    }
    

    private class AsyncFibonacci extends Thread {
        private int toEval;
        private CallBack cont;
        public AsyncFibonacci(int i, CallBack continuation) {
            this.toEval = i;
            this.cont = continuation;
        }
        
        public void run(){
            try{
                cont.setValue(fib(toEval));
            }catch (RemoteException e){
                System.err.println("Call-back error");
            }
        }
    }
}
