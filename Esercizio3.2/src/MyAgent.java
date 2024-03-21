import java.util.*;
import java.rmi.*;

//Estende AbstractAgent caratterizzandolo col codice che vorremmo eseguire; nel 
//caso stampa tutti i nodi visitati fino a quel momento e richiede all�utente di inserire 
//delle informazioni dallo Standard Input. Dopodich� migra verso il nodo successivo 
//fin quando non completa il giro. 

public class MyAgent extends AbstractAgent{
	
	private static final long serialVersionUID = 1L;
	//I nodi che l'agente intende visitare -> L'obiettivo dell'agente � quello di spostarsi da un nodo all'altro
	//del sistema ed eseguire operazioni locali a ciascun nodo per poi portare a destinazione il risultato delle 
	//diverse interazioni
	private Node[] nodesToVisit;
	//variabile che tiene traccia dei nodi visitati
	private int visited;
	//lista per mantenere le informazioni raccolte durante le interazioni
	private ArrayList<String> nameList;
	 
	//il costruttore riceve l'array di nodi da visitare e inizializza le variabili di istanza
	public MyAgent(Node[] nv) {
		nodesToVisit = nv;
		nameList = new ArrayList<String>(); // lista che dovrà essere costruita
		visited = -1;
	}
	
	//Definisce il comportamento dell'agente, quindi qual � il codice che deve essere eseguito man mano
	//che l'agente si sposta nel sistema	
	public void run() {
		System.out.println(visited + " " + nodesToVisit.length);
	
		//chiede di inserire un nome che verr� salvato nella collezione di stringhe nameList
		if(visited < nodesToVisit.length-1) { 
			Scanner sc = new Scanner(System.in); //prevedo la raccolta di informazioni utilizzando lo scanner
			System.out.println("Inserisci il tuo nome");
			nameList.add(sc.nextLine()); // inserisce
			try {
				//dopo aver recuperato sul nodo le informazioni l'agente chiede di migrare il nodo successivo
				migrateTo(nodesToVisit[++visited]);
			}catch(RemoteException e) {
				System.err.println("Errore in migrazione "+ e);
			}
		}
		else {
			//stampa le informazioni, ovvero le stringhe ottenute
			System.out.println(nameList); //quando l'agente arriverà
			//trasparenza significa scrivere codice ????
		}
	}
}
