
//Ottiene il proxy di un oggetto Math usando il metodo create della factory, ed esegue 
//un insieme di invocazioni per poi arrestare gli ActiveObject

public class ActiveObjectTest {
	public static void main(String[] args) {
		
		//creo una factory di oggetti attivi
		MathFactory af = new MathActiveObjectFactory();
		//creo un oggetto di tipo Math
		Math a = af.create(); 
		
		long startTime = System.currentTimeMillis();
		
		//all'interno del ciclo vado ad invocare il metodo add che � il metodo del proxy che produrr�
		//la costruzione di oggetti call e quindi l'attivazione di chiamate asincrone
		//se fosse sincrona il risultato lo avrei qui (prima della stampa successiva)
		for(int i = 0; i < 100; i++) {
			a.add(2*i, 3*i);
		}
		// con get posso calcolarmi il tempo dlel'esecuzione 
		long elapsed = System.currentTimeMillis() - startTime; //mi calcolo il tempo dopo le invocazioni (tempo corrente - tempo d'inizio)
		
		//questa stampa sar� visualizzata prima delle stampe previste nel metodo add a causa dell'asincronia
		System.out.println("All calls finished: " + elapsed + " ms elapsed");
		System.out.flush();
		ActiveObject.schedulers.interrupt();
	}

}
