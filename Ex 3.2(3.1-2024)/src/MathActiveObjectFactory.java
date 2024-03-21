import java.lang.reflect.Proxy;

public class MathActiveObjectFactory implements MathFactory {
	
	public Math create() {
		return (Math)Proxy.newProxyInstance(
					ActiveObjectTest.class.getClassLoader(), 
					new Class<?>[] {Math.class}, 
					new ActiveObject(new MathImpl())
				);			
	}

}
