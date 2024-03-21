//Proxy behaviour

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AdderHandler  implements InvocationHandler {

    private Object realSubject;

    public AdderHandler(Object realSubject) {
        this.realSubject = realSubject;
    }


    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("Debug: hai invocato il metodo: " + method );

        try{
            return method.invoke(realSubject, objects); // objects == args
        }catch (InvocationTargetException e){
            throw e.getCause();
        }
    }
}
