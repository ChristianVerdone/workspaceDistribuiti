package application;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class ProfileInterceptor {

	@AroundInvoke
	Object profile(InvocationContext ctx) throws Exception{
		double startTime= System.nanoTime();
		try {
			return ctx.proceed();
		}finally {
			double endTime= System.nanoTime();
			System.out.println("startTime: "+ startTime+ " stopTime: "+endTime+ " elapsed: "+(endTime-startTime));
		}
	}
}
