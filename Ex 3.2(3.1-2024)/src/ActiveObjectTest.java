
public class ActiveObjectTest {
	public static void main(String[] args) {
		MathFactory af = new MathActiveObjectFactory();
		Math a = af.create();
		
		long startTime = System.currentTimeMillis();
		
		for (int i = 0; i < 100; i++) {
			a.add(2*i, 3*i); //100 invocazioni del metodo, non esecuzioni perchè abbiamo la coda
		}
				
		long elapsed = System.currentTimeMillis() - startTime; // tempo necessario ad invocare 100 metodi
		
		System.out.println("All calls finished: " + elapsed + " ms");
		System.out.flush();
		
		//ActiveObject.schedulers.interrupt();  //durante il for il client accoda le richieste e ci mette meno tempo dell'active
		//object a restituire il risultato dell'elab.
	}
}

//Il pattern proxy può essere utilizzato per nascondere la creazione del messaggio, dando l’impressione di star 
//operando direttamente sull’oggetto passivo (detto servant) e abilitando la trasparenza rispetto agli accessi. 