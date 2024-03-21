import java.util.concurrent.Future;

public interface AsyncComputeEngine {
	public Future <Object> process(Task t);
}
