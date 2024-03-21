//Implementa Math. Non estende UnicastRemoteObject
//chi effettua la somma deve visualizzare la somme sullo standard output
//avremmo potuto prevedere una callBack
public class MathImpl implements Math{
	
	public void add(float x, float y) {
		
		double sum = x + y;
		
		System.out.println(x+" + "+y+" = "+sum);
		//callback.addResult(x,y,sum);
	}

}
