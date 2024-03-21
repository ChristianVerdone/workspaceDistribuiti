public class AddTask implements Task{

    private static final long serialVersionUID = 1L;
	private int a;
    private int b;

    public AddTask(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public Object execute(){
        return (long) (a+b);
    }

}
