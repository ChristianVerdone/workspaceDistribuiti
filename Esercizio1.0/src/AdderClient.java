import java.rmi.*;
import java.util.*;
import java.net.*;

public class AdderClient {
   public static void main(String[] args) {
      Scanner s = null; // Inizializzazione di un oggetto Scanner per leggere input da console
      try {
         // Recupero dell'oggetto remoto dal registro RMI locale all'indirizzo 127.0.0.1 con il nome "adder"
         Adder a = (Adder)Naming.lookup("rmi://127.0.0.1/adder");

         // Richiesta all'utente di inserire i numeri da sommare
         System.out.println("Inserisci i numeri da sommare");
         s = new Scanner(System.in); // Inizializzazione di Scanner per leggere input da console
         int op1 = s.nextInt(); // Lettura del primo numero
         int op2 = s.nextInt(); // Lettura del secondo numero

         // Calcolo della somma utilizzando il metodo remoto "add" dell'oggetto Adder
         System.out.println("La somma di " + op1 + " + " + op2 + " e' " + a.add(op1, op2));
      } catch (NotBoundException e) {
         // Eccezione lanciata se l'oggetto richiesto non è presente nel registro RMI
         System.err.println("Request object not bound "+ e);
      } catch (MalformedURLException e) {
         // Eccezione lanciata se l'URL utilizzato per accedere all'oggetto remoto è errato
         System.err.println("Wrong URL" + e);
      } catch (RemoteException e) {
         // Eccezione lanciata in caso di errori di rete o del server RMI
         System.err.println("Network or Server Error" + e);
      } finally {
         // Chiusura dello scanner per liberare le risorse
         if (s != null) s.close();
      }
   }
}
