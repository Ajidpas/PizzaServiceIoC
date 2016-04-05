package pizza.infrastructure;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;

import pizza.infrastructure.annotations.BenchMark;

public class MyInvocationHandler implements InvocationHandler {
	
	private Object bean;
	
	private Class<?> clazz;
	
	public MyInvocationHandler(Object bean, Class clazz) {
		this.bean = bean;
		this.clazz = clazz;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Method[] methods = clazz.getMethods();
		for (Method currentMethod : methods) {
			if (currentMethod.getName().equals(method.getName()) 
					&& currentMethod.getAnnotation(BenchMark.class) != null) {
				return runMethodWithTimeChecking(method, args);
			}
		}
		return method.invoke(bean, args);
	}

	private Object runMethodWithTimeChecking(Method method, Object[] args)
			throws IllegalAccessException, InvocationTargetException {
		long nanosecondsBefore;
		long nanosecondsAfter;
		nanosecondsBefore = System.nanoTime();
		Object obj = method.invoke(bean, args);
		nanosecondsAfter = System.nanoTime();
		long nanosecondsResult = nanosecondsAfter - nanosecondsBefore;
		System.out.println("Method " + method.getName() + " worked " + nanosecondsResult + " nanoseconds.");
		return obj;
	}

}
