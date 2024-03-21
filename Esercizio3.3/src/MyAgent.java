import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyAgent extends AbstractAgent {
	private static final long serialVersionUID = 1L;
	private Node[] nodesToVisit;
	private int visited;
	private ArrayList<String> nameList;

	public MyAgent(Node[] nv) {
		nodesToVisit = nv;
		nameList = new ArrayList<String>();
		visited = -1;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(visited + " " + nodesToVisit.length);
		if (visited < nodesToVisit.length - 1) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Inserisci il tuo nome ");
			nameList.add(sc.nextLine());
			try {
				migrateTo(nodesToVisit[++visited]);
			} catch (RemoteException e) {
				System.err.println("Errore in migrazione " + e);
			}
		} else {
			System.out.println(nameList);
		}
	}

}
