
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.LinkedBlockingDeque;

public class ActiveObject implements InvocationHandler {
    private LinkedBlockingDeque<Call> activationQueue = new LinkedBlockingDeque<Call>();
    private Object servant;
    private Thread scheduler;

    public static final ThreadGroup schedulers = new ThreadGroup("ActiveObjectSchedulers");

    static {
        Runtime.getRuntime().addShutdownHook(new ActiveObject.ShutHook());
    }

    //todo
    public ActiveObject(Object servant) {
        this.servant = servant;
        this.scheduler = new Scheduler();
        this.scheduler.start();
    }


    private void enqueue(Call call) {
        activationQueue.add(call);
    }

    private Call dequeue() throws InterruptedException {
        return activationQueue.take();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Call call = new Call(method, args);
        enqueue(call);
        return null;
    }

    private class Scheduler extends Thread {
        public Scheduler() {
            super(schedulers, "Scheduler");
        }

        public void run() {
            boolean halt = false;
            while (!(halt && activationQueue.size() == 0)) {
                try {
                    Call call = dequeue();
                    dispatch(call);
                } catch (Exception e) {
                    halt = true;
                }
            }
            System.out.println();
        }

        private void dispatch(Call call) throws InvocationTargetException, IllegalAccessException {
            call.method.invoke(servant, call.args);
        }
    }

    private static class Call {
        public Method method;
        public Object[] args;

        public Call(Method method, Object[] args) {
            this.method = method;
            this.args = args;
        }
    }

    public static class ShutHook extends Thread {
        public void run() {
            try {
                schedulers.interrupt();
                Thread[] sched = new Thread[schedulers.activeCount()];
                int schedNum = schedulers.enumerate(sched);
                for (int i = 0; i < schedNum; i++) {
                    sched[i].join();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}