import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class PrintServiceClient {
    public static void main(String[] args) {
        Scanner s = null;
        String msg = null;
        try {
            PrintService a = (PrintService) Naming.lookup("rmi://127.0.0.1/printService");
            System.out.println("Inserisci il messaggio da stampare:");
            s = new Scanner(System.in);
            do {
            	msg = s.nextLine();
            	a.print(msg);
            }while(!msg.equals("."));
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
