import java.rmi.Remote;

import java.rmi.RemoteException;

//Nel pattern factory � l�interfaccia dei creator, infatti definisce il prototipo del 
//metodo create che dovr� ritornare un oggetto di tipo PrintService.
//Estende a sua volta Remote per poter essere registrato come oggetto remoto.

//La factory diventa un oggetto remoto che ha come compito quello di creare in remoto oggetti
//Quindi definiamo un oggetto remoto che prevede la creazione di oggetti in remoto


public interface PrintServiceFactory extends Remote{
	public PrintService create() throws RemoteException;

}
