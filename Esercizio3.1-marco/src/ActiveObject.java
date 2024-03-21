import java.lang.reflect.*;
import java.util.concurrent.*;

//Associa un thread di controllo (scheduler) e una coda di invocazioni ad un oggetto 
//passivo detto servant. Prevede anche l�implementazione dell�interfaccia 
//InvocationHandler per supportare i proxy dinamici. Se guardiamo l'ActiveObject come
//una particolare implementazione di un proxy, il comportamento che vogliamo conferire al quel proxy
//� di reificare l'invocazione dei metodi, trasformarli in oggetti chiamata, accodare questi oggetti chiamati
//per consentirne una successiva elaborazione
//Mantiene anche un 
//ThreadGroup per gestire tutti gli scheduler
public class ActiveObject implements InvocationHandler{
	
	//coda che viene utilizzata dall'oggetto attivo per accodare le chiamate
	private LinkedBlockingQueue<Call> activationQueue = new LinkedBlockingQueue<Call>();
	//Servant � l'oggetto che di volta in volta contiene l'implentazione effettiva (stato+metodi)
	//dell'oggetto che vogliamo rendere attivo
	private Object servant;
	
	//Questo thread � presente come classe privata; alla creazione si occupa di 
	//aggiungersi al gruppo di scheduler.
	//Lo scheduler � un Thread e render� attivo l'oggetto Servant
	private Thread scheduler;
	//ThreadGroup usato per etichettare tutti i Thread scheduler che saranno creati come conseguenza 
	//della creazione degli oggetti attivi
	public static final ThreadGroup schedulers = new ThreadGroup("ActiveObjectSchedulers");
	
	//recuperiamo il runTime di Java e invochiamo addShutDownHook sul runTime per impiantare nella macchina
	//virtuale uno specifico Hook di shutDown. Quindi quando facciamo control+c sar� intercettato e sar� 
	//mandato in esecuzione il codice previsto in questo hook
	//l'utilizzo del blocco statico serve per far s� che questo codice viene mandato in esecuzione quando la
	//classe viene caricata
	//static {Runtime.getRuntime().addShutdownHook(new ActiveObject.ShutHook()); }
	
	//Nel costruttore inizializziamo le variabili di istanza, accettando il servant come parametro
	//Viene anche avviato lo scheduler
	//Quindi quando creiamo un'instanza di ActiveObject viene creato internamente uno scheduler e la coda
	//Mentre il Servant viene passato dall'esterno perch� vogliamo poter trasformare qualunque 
	//oggetto passivo in oggetto attivo
	public ActiveObject(Object servant) {
		this.servant = servant;
		this.scheduler = new Scheduler();
		this.scheduler.start();
	}
	
	//Riceve una chiamata e la inserisce in coda
	public void enqueue(Call call) {
		activationQueue.add(call);
	}
	
	//Estrae dalla coda una chiamata (call)
	public Call dequeue() throws InterruptedException{
		return activationQueue.take();
	}
	
	//ActiveObject discende dall'interfaccia InvocationHandler e quindi riscrive il metodo invoke
	//che ritorna null perch� asincrono. Si occupa di creare una nuova Call a partire dal 
	//metodo invocato e accodarla.
	//Vogliamo che l'invocazione sul proxy che determina l'invocazione di questo metodo produca un oggetto
	//Call e infatti preleviamo method e args e inseriamo nella Call, successivamente inserita nella coda
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
		Call call = new Call (method, args);
		enqueue(call);
		//non restituiamo nulla perch� accodiamo soltanto la Call
		return null;
	}
	
	//Lo scheduler � in sostanza un Thread
	private class Scheduler extends Thread{
		 private boolean isInterrupted;
		//Ogni volta che viene creato uno Scheduler si iscrive al ThreadGroup, quindi sar� controllabile
		//tramite quel gruppo
		public Scheduler() { super(schedulers, "Scheduler");}
		
		//Nel ciclo del run si estraggono le Call dalla coda 
		//e si procede con il dispatching del metodo incapsulato al suo interno e richiamabile 
		//sul servant.
		// Il thread viene interrotto dall�esterno tramite interrupt, ma se la coda 
		//non � vuota si differisce la terminazione al suo svuotamento, quindi � necessaria 
		//una doppia condizione.
		//Quindi se interrompiamo il Thread l'applicazione non viene subito interrotta ma
		//si aspetta che tutti i messaggi in coda vengano elaborati
		public void run() {
			//boolean halt = false;
			while(!(isInterrupted && activationQueue.size() == 0)) {
				try {
					//Scheduler � interna ad ActiveObject, quindi possiamo utilizzare il metodo
					//dequeue della classe ActiveObject
					System.out.println("lunghezza queue: " + activationQueue.size());
					Call call = dequeue();
					dispatch(call);
					
				}
				catch (InterruptedException e) {
					isInterrupted = true;
				}
				catch(Exception e) {
					//quando halt � true si esce dal ciclo e termina run
					//se non ci sono altri thread il programma termina
					//halt = true;
					System.out.println(e);}
				}
			System.out.println("Sta terminando il thread " + this + " " + activationQueue.size());
			}
		
		//in dispatch l'oggetto Call sar� utilizzato per dar vita alla chiamata effettiva
		//accedo alla variabile di istanza method e lo invoco sul servant (il realSubject del proxy) e passo
		//gli argomenti estratti dalla chiamata
		private void dispatch(Call call) throws IllegalArgumentException, InvocationTargetException, IllegalAccessException{
			call.method.invoke(servant, call.args);
		}
	}
	
	//La classe Call � privata e statica, quindi eventuali modifiche alle variabili
	//di istanza (dichiarate public) siano limitate alla sola classe ActiveObject. Possiamo 
	//permetterci di violare l�information hiding evitando di usare get e set
	//e rendere l�accesso pi� efficiente e veloce sapendo che nessuno al di fuori di activeObject pu� vedere tale classe. 
	//Un oggetto Call � caratterizzato dal metodo che si desidera invocare e dall�array di 
	//argomenti che accetta, ricorrendo alla reflection restituendo un oggetto call.
	private static class Call{
		
		public Call(Method m, Object[] a) {
			method = m;
			args = a;
		}
		
		public Method method;
		public Object[] args;
	}
	
	
	//Infine, abbiamo una terza classe privata che � ShutHook, a sua volta un Thread, per 
	//gestire lo shutdown forzato dall�esterno e interrompere tutti i thread appartenenti 
	//al gruppo tramite interrupt. � connesso ad una porzione di codice statica posta in ActiveObject, la 
	//quale prevede il recupero del Java runtime appena la classe verr� caricata, 
	//mandando in esecuzione ShutHook. 
	//public static class ShutHook extends Thread{
		
	//	public void run() {
	//		try {
	//			schedulers.interrupt();
	//			System.out.println("Hook invocato");
				//L'hook aspetta che ciascuno dei thread presenti nel gruppo deve terminare col metodo join
				//quindi join sospende l'esecuzione finch� tutti i thread non sono completati
				//questo per evitare situazioni di inconsistenza
	//			Thread[] sched = new Thread[schedulers.activeCount()];
				//int schedNum = schedulers.enumerate(sched);
	//			for(int i = 0; i < sched.length; i++) {
	//				sched[i].join();
	//		}
	//		}catch(InterruptedException e) {}
	//	}
	// }
}
	
