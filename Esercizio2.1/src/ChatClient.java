import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) {
        Scanner sc = null;

        try{
            Subject s = (Subject) Naming.lookup("rmi://127.0.0.1/chatSubject");
            // al client diamo il proxy dell observer
            Observer o = new ChatObserverImpl();
            s.attach(o); //impianto l'observer per ricevere i messaggi e attach lo fa sapere al subject

            sc= new Scanner(System.in);
            String msg = null;
            do {
            	 System.out.println("Scrivi> ");
                 System.out.flush();
                 msg = sc.nextLine();
                 s.notify(msg);
            }while(!msg.endsWith("."));
            
            /* implementazione alternativa che non termina l'esecuzione
            while (true){
                System.out.println("Scrivi> ");
                System.out.flush();
                s.notify(sc.nextLine());
            }
            */
        }catch (NotBoundException e) {
            System.err.println("Request obect not bound " + e);
        } catch (MalformedURLException e) {
            System.err.println("Wrong URL" + e);
        } catch (RemoteException e) {
            System.err.println("Network or Server Error" + e);
        } finally {
            if (sc != null) sc.close();
        }
    }
}
