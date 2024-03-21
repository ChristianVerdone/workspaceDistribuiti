import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class PrintServiceClient {
    public static void main(String[] args) {
        Scanner s = null;
        try {
            // Cerca l'oggetto remoto "PrintService" tramite il nome "rmi://127.0.0.1/printServiceUser"
            PrintService a = (PrintService) Naming.lookup("rmi://127.0.0.1/printServiceUser");

            // Crea un oggetto di tipo "Person"
            Person u = new Person("Christian", "Verdone", 25);

            // Chiama il metodo "print" sull'oggetto remoto con l'oggetto "Person" come argomento
            a.print(u);

        } catch (NotBoundException e) {
            System.err.println("Request object not bound: " + e);
        } catch (MalformedURLException e) {
            System.err.println("Wrong URL: " + e);
        } catch (RemoteException e) {
            System.err.println("Network or Server Error: " + e);
        } finally {
            if (s != null) s.close();
        }
    }
}

/*
- La classe "PrintServiceClient" rappresenta un client RMI che cerca e chiama un oggetto remoto "PrintService".
- Utilizza "Naming.lookup" per cercare l'oggetto remoto tramite l'URL "rmi://127.0.0.1/printServiceUser".
- Crea un oggetto di tipo "Person" e chiama il metodo "print" sull'oggetto remoto con l'oggetto "Person" come argomento.
- Gestisce le eccezioni associate alla ricerca, all'URL errato e agli errori di rete o del server.
*/
