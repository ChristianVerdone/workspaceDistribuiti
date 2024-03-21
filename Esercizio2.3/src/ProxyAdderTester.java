import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class ProxyAdderTester {

    public static void main(String[] args) {
        InvocationHandler ih = new AdderHandler(new AdderImpl());
        ClassLoader loader = ProxyAdderTester.class.getClassLoader();

        Adder a = (Adder) Proxy.newProxyInstance(loader, new Class<?>[] {Adder.class}, ih);
        System.out.println("Adder a.add() = " + a.add(2,6));



        AdderFactory af = new AdderLoggerFactory();
        Adder a1 = af.create();
        System.out.println("Adder con Factory a1.add() = " + a1.add(8,9));


    }
}
