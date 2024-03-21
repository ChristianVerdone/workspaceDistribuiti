
public class ProxyMathTester {
	public static void main(String[] args) {
		/*
		InvocationHandler ih = new MathLoggerHandler(new MathImpl());
		Math m = (Math)Proxy.newProxyInstance(ProxyMathTester.class.getClassLoader(), new Class[] {Math.class}, ih);
		System.out.println(m.add(3, 5));
		
		*/
		MathFactory mf = new MathLoggerFactory(true);
		Math m1 = mf.create();
		System.out.println(m1.add(5, 6));
		System.out.println(m1.div(100, 5));
	}
}
