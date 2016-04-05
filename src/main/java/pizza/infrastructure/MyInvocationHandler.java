package pizza.infrastructure;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Calendar;

public class MyInvocationHandler implements InvocationHandler {
	
	private Object bean;
	
	public MyInvocationHandler(Object bean) {
		this.bean = bean;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		long nanosecondsBefore;
		long nanosecondsAfter;
		nanosecondsBefore = System.nanoTime();
		method.invoke(bean, args);
		nanosecondsAfter = System.nanoTime();
		long nanosecondsResult = nanosecondsAfter - nanosecondsBefore;
		System.out.println("Method " + method.getName() + " worked " + nanosecondsResult + " nanoseconds.");
		return null;
	}

}
