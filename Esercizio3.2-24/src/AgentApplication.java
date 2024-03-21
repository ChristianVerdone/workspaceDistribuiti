public class AgentApplication {

	public static void main(String[] args) {
		//creo il percorso degli agenti
		Node[] tour = new Node[3];
		try {
			for (int i = 0; i < 3; i++)
				tour[i] = new Node(i);
			Agent agent = new MyAgent(tour);
			agent.start();
		} catch (NodeLinkingException e) {
			System.err.println("Errore nella creazione del link verso il nodo " + e);
		}
	}
}
