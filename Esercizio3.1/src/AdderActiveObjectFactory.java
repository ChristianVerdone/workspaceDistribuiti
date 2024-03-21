import java.lang.reflect.Proxy;

public class AdderActiveObjectFactory implements AdderFactory{
    public Adder create(){
        return (Adder) Proxy.newProxyInstance(ActiveObject.class.getClassLoader(), new Class<?>[] {Adder.class}, new ActiveObject(new AdderImpl()));
    }
}
