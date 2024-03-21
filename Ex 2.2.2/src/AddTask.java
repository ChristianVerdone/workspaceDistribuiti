
public class AddTask implements Task {

	private static final long serialVersionUID = 1L;
	private int a, b;
	
	public AddTask(int a, int b){
		this.a = a;
		this.b = b;
		
	}
	
	@Override
	public Object call() throws Exception {
		return (long) (a + b);
	}

}
