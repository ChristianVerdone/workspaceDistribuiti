import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ComputeEngineClient {

    public static void main(String[] args) {
        try{

            ComputeEngine computeEngine = (ComputeEngine) Naming.lookup("rmi://127.0.0.1/computeEngine");

            // Add task
            Task addTask = new AddTask(40,18);
            long resultAdd = (long)computeEngine.process(addTask);
            System.out.println("resultAdd = " + resultAdd);

            //Fact Task
            Task factTask = new FactTask(5);
            long resultFact = (long)computeEngine.process(factTask);
            System.out.println("resultFact = " + resultFact);


        }catch (NotBoundException e) {
            System.err.println("Request obect not bound " + e);
        } catch (MalformedURLException e) {
            System.err.println("Wrong URL" + e);
        } catch (RemoteException e) {
            System.err.println("Network or Server Error" + e);
        }
    }
}
