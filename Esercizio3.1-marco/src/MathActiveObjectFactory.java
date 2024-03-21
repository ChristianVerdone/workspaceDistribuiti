import java.lang.reflect.Proxy;

//È una factory concreta che restituirà il proxy di MathImpl creato dinamicamente.
//Notiamo che l’InvocationHandler è proprio ActiveObject
//Vogliamo dar vita ad un oggetto attivo e al relativo proxy
public class MathActiveObjectFactory implements MathFactory{
	
	//Il metodo create va a creare un Proxy di Math (interfaccia di cui vogliamo realizzare il Proxy)
	//e l'Handler che passiamo è un'instanza di ActiveObject che sia in grado di operare su un Servant
	//di tipo MathImpl
	public Math create() {
		return (Math) Proxy.newProxyInstance(ActiveObjectTest.class.getClassLoader(), 
				new Class<?>[] {Math.class}, 
				new ActiveObject(new MathImpl()));
	}

}
