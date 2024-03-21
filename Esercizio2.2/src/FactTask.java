public class FactTask implements Task {

    private static final long serialVersionUID = 1L;
	long n;

    public FactTask(long n) {
        this.n = n;
    }

    @Override
    public Object execute() {
        return (long)fact(n);
    }

    private long fact(long n){
        if (n <= 1){
            return 1;
        }else{
            return n * fact(n-1);
        }
    }
}
