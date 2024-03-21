import java.lang.reflect.Proxy;

public class MathActiveObjectFactory implements MathFactory {
   
    // Sovrascrive il metodo della MathFactory per creare un'istanza di Math.
    @Override
    public Math create(boolean ao) {
        // Se il parametro booleano ao è true, crea un Active Object per Math.
        return ao ?
                // Crea un proxy dinamico per l'interfaccia Math utilizzando la classe Proxy.
                (Math)Proxy.newProxyInstance(
                        // ClassLoader utilizzato per caricare la classe MathActiveObjectFactory.
                        MathActiveObjectFactory.class.getClassLoader(),
                        // Array di interfacce che il proxy implementerà, contenente solo l'interfaccia Math.
                        new Class<?>[]{Math.class},
                        // Handler di invocazione che gestisce le chiamate ai metodi sull'oggetto proxy.
                        new ActiveObject(new MathImpl()) // ActiveObject riceve un'istanza di MathImpl come "servant".
                ) :
                // Se ao è false, crea semplicemente un'istanza di MathImpl.
                new MathImpl();
    }

}