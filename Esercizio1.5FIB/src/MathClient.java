import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class MathClient {

    public static void main(String[] args) {
        Scanner s = null;

        try {


            s = new Scanner(System.in);
            Math math = (Math) Naming.lookup("rmi://127.0.0.1/math");
            // sync
            System.out.println("Inserisci il numero di cui vuoi calcolare il numero di fibonacci:");
            int fib1 = math.getFibonacciOf(s.nextInt());
            System.out.println("fib1 = " + fib1);

            // async
            System.out.println("Inserisci il numero di cui vuoi calcolare il numero di fibonacci asincrono");
            CallBack c = new CallBackImpl();
            int fib2 = math.getAsyncFibonacciOf(s.nextInt(), c);
            for (int i = 0; i < 150; i++) {
                System.out.println("Ti dimostro che l'esecuzione � asincrona");
            }
            System.out.println("fib2 = " + fib2);
        } catch (NotBoundException e) {
            System.err.println("Request obect not bound " + e);
        } catch (MalformedURLException e) {
            System.err.println("Wrong URL" + e);
        } catch (RemoteException e) {
            System.err.println("Network or Server Error" + e);
        } finally {
            if (s != null) s.close();
        }
    }
}