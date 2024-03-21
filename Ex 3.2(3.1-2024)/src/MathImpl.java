public class MathImpl implements Math {
	public void add(float x, float y){
		double sum = x + y;
		System.out.println(x+"+"+y+"="+sum);
		// callback.addResult(x, y, sum);
	}
}