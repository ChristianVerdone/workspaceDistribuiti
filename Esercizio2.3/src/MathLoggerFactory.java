import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MathLoggerFactory implements MathFactory {
	private boolean loggable; //  indica se la factory deve creare oggetti Math con funzionalità di logging abilitate o disabilitate
	public MathLoggerFactory(boolean l) {
		loggable = l; // Viene definito un costruttore per la classe MathLoggerFactory che accetta un booleano come parametro. Questo booleano determina se il logging deve essere abilitato (true) o disabilitato (false).
	}
	public Math create() {
		if (loggable) { // verifica se il logging è abilitato o meno
			InvocationHandler ih = new LoggerHandler(new MathImpl()); 
			return (Math)Proxy.newProxyInstance(ProxyMathTester.class.getClassLoader(), new Class[] {Math.class}, ih); //Viene restituita un'istanza di Math creata tramite Proxy.newProxyInstance. Questa istanza sarà un proxy per l'oggetto reale MathImpl, e il suo comportamento sarà gestito dall'InvocationHandler ih
		} else
			return new MathImpl();
	}

	
	//viene utilizzato un idioma, che è un pattern specifico a seconda del linguaggio che utilizziamo
	
}