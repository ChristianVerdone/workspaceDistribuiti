import java.lang.reflect.Proxy;

public class MathActiveObjectFactory implements MathFactory {
	
	@Override
	public Math create(boolean ao) {
		return (Math)Proxy.newProxyInstance(
					MathActiveObjectFactory.class.getClassLoader(), 
					new Class<?>[] {Math.class}, 
					new ActiveObject(new MathImpl())
				);
	}

}
