
public class FibTask implements Task{
	
	private static final long serialVersionUID = 1L;
	private int n;

	
	 public FibTask(int n) {
	       this.n= n;
	    }


	@Override
	public Object call() throws Exception {
		
            return getFibonacciNumberAt(this.n);
	}
	
	
	private int getFibonacciNumberAt(int n) {
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        else
            return getFibonacciNumberAt(n - 1) + getFibonacciNumberAt(n - 2);
    }
	
}
