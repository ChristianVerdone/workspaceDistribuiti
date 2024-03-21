// Implementazione manuale Proxy
class MathProxy implements Math {
	private Math realsubject;
	public MathProxy(Math rs) {
		realsubject = rs;
	}
	public double add(float a, float b) {
		System.out.println("Debug: Hai invocato add");
		return realsubject.add(a, b);
	}
	@Override
	public double sub(float a, float b) {
		System.out.println("Debug: Hai invocato sub");
		return realsubject.sub(a, b);
	}
	@Override
	public double mul(float a, float b) {
		System.out.println("Debug: Hai invocato mul");
		return realsubject.mul(a, b);
	}
	@Override
	public double div(float a, float b) {
		System.out.println("Debug: Hai invocato div");
		return realsubject.div(a, b);
	}
}