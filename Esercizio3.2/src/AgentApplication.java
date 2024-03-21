
//Crea una lista di nodi, un agente da eseguire e avvia il tour.
public class AgentApplication {
	public static void main(String[] args) {
		//Creo un tour di 3 nodi
		Node[] tour = new Node[3];
		try {
			//carichiamo nell'array i nodi con la numerazione per raggiungere i diversi container
			for(int i = 0; i < 3; i++) tour[i] = new Node(i);
			//Creiamo l'agente e gli passiamo l'array di nodi da visitare
			Agent agent = new MyAgent(tour);
			//diamo vita all'esecuzione dell'agente
			agent.start();
		}catch(NodeLinkingException e) {
			System.err.println("Errore nella creazione del link verso il nodo" + e);
		}
	}

	//il client sposta l'agent nei nodi 
}
