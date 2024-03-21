import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class AdderLoggerFactory implements AdderFactory {
    @Override
    public Adder create() {
        InvocationHandler ih = new AdderHandler(new AdderImpl());
        ClassLoader loader = ProxyAdderTester.class.getClassLoader();

        return (Adder) Proxy.newProxyInstance(loader, new Class<?>[] {Adder.class}, ih);
    }
}
