import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MathLoggerFactory implements MathFactory {
	private boolean loggable;
	public MathLoggerFactory(boolean l) {
		loggable = l;
	}
	public Math create() {
		if (loggable) {
			//qui utilizziamo un idioma che è un implementazione specifica di un pattern utilizzando uno specifico linguaggio.
			// creo un ih e gli passo il realsubject
			InvocationHandler ih = new LoggerHandler(new MathImpl()); 
			// crea un proxy che abbia i metodi dell'interfaccia math e il comportamento di "LoggerHandler"
			// return new MathProxy(new MAthImpl()); implementazione canonica
			/* creo un proxy di tipo math
			 * il loader è quello di default della classe ProxyMathTester ma è possibile crearne uno nello specifico
			 * il comportamento è definito dalla classe, la creazione del proxy avviene allo stesso modo sempre perchè
			 * basta cambiare la classe.
			 */
			return (Math)Proxy.newProxyInstance(ProxyMathTester.class.getClassLoader(), new Class[] {Math.class}, ih);
		} else
			return new MathImpl();
	}

}
/*
// La factory può applicare o no il logging a seconda del valore booleano l
// invocation handler indica il comportamento del proxy
// new Class[] {Math.class} indica le interfacce utilizzate per istanziare il proxy
// utilizzo il classloader di ProxyMathTester con ProxyMathTester.class.getClassLoader()

// abbiamo diviso il proxy in due aspetti:
// parte strutturale gestita dalla reflection e parte comportamentale dall'handler
// reflection(trasforma l'invocazione di metodi in oggetti Method)

// creo un proxy che abbia i metodi dell'interfaccia Math e il comportamento  dell'ih
*/