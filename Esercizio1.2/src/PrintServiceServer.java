import java.net.MalformedURLException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

// Definisce la classe "PrintServiceServer"
public class PrintServiceServer {
    public static void main(String args[]) {
        try {
            // Crea un'istanza dell'oggetto remoto "PrintServiceImpl"
            PrintService a = new PrintServiceImpl();

            // Crea un registro RMI sulla porta 1099
            LocateRegistry.createRegistry(1099);

            // Associa l'oggetto remoto all'URL "rmi://127.0.0.1/printServiceUser"
            Naming.bind("rmi://127.0.0.1/printServiceUser", a);
        } catch (AccessException e) {
            System.err.println("Bind operation not permitted");
        } catch (RemoteException e) {
            System.err.println("Registry could not be contacted");
        } catch (MalformedURLException e) {
            System.err.println("Wrong URL for binding");
        } catch (AlreadyBoundException e) {
            System.err.println("Object already bound to the registry");
        }
    }
}

/*
- La classe "PrintServiceServer" rappresenta un server RMI che registra un oggetto remoto.
- Crea un'istanza dell'oggetto remoto "PrintServiceImpl".
- Crea un registro RMI sulla porta 1099.
- Associa l'oggetto remoto all'URL "rmi://127.0.0.1/printServiceUser".
- Gestisce le eccezioni associate all'accesso, alla comunicazione remota e all'URL errato.
*/
