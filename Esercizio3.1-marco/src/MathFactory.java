//Nel pattern factory � l�interfaccia dei creator, infatti definisce il prototipo del 
//metodo create che dovr� ritornare un oggetto di tipo Math. 
public interface MathFactory {  //trasformo invocazioni sincroni in invocazioni asincrone
	public Math create();

}
