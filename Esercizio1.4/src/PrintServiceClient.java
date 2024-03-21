import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class PrintServiceClient {
    public static void main(String[] args) {
        Scanner s = null;
        try {
            PrintServiceFactory factory = (PrintServiceFactory) Naming.lookup("rmi://127.0.0.1/printServiceFactory");
            PrintService ps = factory.create();
            User u = new User("Christian", "Verdone", 25);
            ps.print(u);

        } catch (NotBoundException e) {
            System.err.println("Request obect not bound "+ e);
        } catch (MalformedURLException e) {
            System.err.println("Wrong URL" + e);
        } catch (RemoteException e) {
            System.err.println("Network or Server Error" + e);
        } finally {
            if (s != null) s.close();
        }

    }
}
