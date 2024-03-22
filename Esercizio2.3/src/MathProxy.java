// Implementazione manuale Proxy
// MathProxy dovrà fornire implementazioni per tutti i metodi definiti nell'interfaccia Math

class MathProxy implements Math {
	private Math realsubject;
	public MathProxy(Math rs) {
		realsubject = rs;
	}
	
	//metodo add
	public double add(float a, float b) {
		System.out.println("Debug: Hai invocato add");
		return realsubject.add(a, b);
	}
	@Override
	//metodo sub
	public double sub(float a, float b) {
		System.out.println("Debug: Hai invocato sub");
		return realsubject.sub(a, b);
	}
	
	//metodo mul
	@Override
	public double mul(float a, float b) {
		System.out.println("Debug: Hai invocato mul");
		return realsubject.mul(a, b);
	}
	@Override
	
	//metodo div
	public double div(float a, float b) {
		System.out.println("Debug: Hai invocato div");
		return realsubject.div(a, b);
	}
}