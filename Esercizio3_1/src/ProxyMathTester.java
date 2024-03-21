
public class ProxyMathTester {
	public static void main(String[] args) {
 //esegue alcuni test utilizzando una factory per creare oggetti Math con funzionalità di logging
		
	
		// Viene creato un oggetto MathFactory chiamato mf utilizzando una MathLoggerFactory. Il parametro true indica che si desidera abilitare il logging.
		MathFactory mf = new MathLoggerFactory(true);
		Math m1 = mf.create();
		// Viene chiamato il metodo add sull'oggetto m1 e il risultato viene stampato sulla console
		System.out.println(m1.add(5, 6));
		
		//stessa cosa per div
		System.out.println(m1.div(100, 5));
	}
}
