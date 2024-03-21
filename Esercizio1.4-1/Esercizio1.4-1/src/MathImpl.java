import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/*Estende UnicastRemoteObject, l'oggetto remoto e il RealSubject che implementa l'interfaccia Math. 
 * Calcola il fattoriale in maniera ricorsiva e lo restituisce sia in modo sincrono che asincrono. 
 * Nel secondo caso accetta un valore intero e un oggetto Callback per restituire il risultato al client appena pronto, 
 * consentendogli di continuare nel mentre l'esecuzione. GetAsynchFactOf avvia un thread che passa al client il fattoriale invocando il metodo
 *  setValue esposto da Callback.
 */

public class MathImpl extends UnicastRemoteObject implements Math{
	
	private static final long serialVersionUID = 1L;
	
	public MathImpl() throws RemoteException{}
	
	//synch
	public int getFactOf(int v) throws RemoteException{
		return fact(v);
	}
	// precondition: n>=0
	private int fact(int n) {
		if(n==0) return 1;
		else return n*fact(n-1);
	}
	
	//Asynch
	
	public void getAsyncFactOf(int v, CallBack cont) {
		//Viene creato un Thread a cui passo il numero di cui calcolare il fattoriale sia la callBack che sarà utilizzata per restituire il fattoriale
		new AsyncFact(v, cont).start();
	}
	
	private class AsyncFact extends Thread{
		private int toEval;
		private CallBack cont;
		public AsyncFact(int v, CallBack c) {
			toEval = v;
			cont = c;
		}
		/*Nel corpo del thread (cioè nel run) viene invocato il metodo fact per calcolare il fattoriale il fattoriale viene passato al client attraverso
		 *  un'invocazione del metodo setValue della CallBack cont, la variabile di tipo callBack che abbiamo passato al thread quindi, quando il risultato 
		 *  sarà disponibile il trhread invocherà sul client il metodo setValue della callBack
		 */
		public void run() {
			try {
				cont.setValue(fact(toEval));
			}catch(RemoteException e) {
				System.err.println("Call-back error");
			}
		}
	}
}
