public class FibTask implements Task {

	private static final long serialVersionUID = 1L;
	int n;

    public FibTask(int n) {
        this.n = n;
    }

    @Override
    public Object call() {
        return (long) fib(n);
    }
    
    private long fib(int n){
        if((n==0) || (n==1)) return 1;
        else return fib(n-1) + fib(n-2);
    }
    
}
