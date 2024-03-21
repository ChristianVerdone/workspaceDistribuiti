public class AgentApplication {

	public static void main(String[] args) {
		//creo il percorso degli agenti
		Node[] tour = new NodeImpl[3];
		try {
			for (int i = 0; i < 3; i++)
				tour[i] = new NodeImpl(i);
			Agent agent = new MyAgent(tour);
			agent.start();
		} catch (NodeLinkingException e) {
			System.err.println("Errore nella creazione del link verso il nodo " + e);
		}

	}
}
