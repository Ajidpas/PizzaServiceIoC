package pizza.infrastructure;

import java.lang.reflect.*;
import java.util.*;
import pizza.infrastructure.annotations.BenchMark;

public class BenchMarkProxyBuilder {

	private Class<?> clazz;

	private Object proxy;

	public Object createProxyForBean(Object bean) {
		clazz = bean.getClass();
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			if (method.getAnnotation(BenchMark.class) != null) {
				createProxy(bean);
				return proxy;
			}
		}
		return bean;
	}

	private void createProxy(Object bean) {
		List<Class<?>> interfaceList = getAllInterfaces(clazz);
		Class<?>[] interfaces = (interfaceList.toArray(new Class[interfaceList.size()]));
		InvocationHandler handler = new MyInvocationHandler(bean, clazz);
		proxy = Proxy.newProxyInstance(clazz.getClassLoader(), interfaces, handler);
	}

	private List<Class<?>> getAllInterfaces(Class<?> cls) {
		if (cls == null) {
			return null;
		}
		LinkedHashSet<Class<?>> interfacesFound = new LinkedHashSet<Class<?>>();
		getAllInterfaces(cls, interfacesFound);
		return new ArrayList<Class<?>>(interfacesFound);
	}

	private void getAllInterfaces(Class<?> cls, HashSet<Class<?>> interfacesFound) {
		while (cls != null) {
			Class<?>[] interfaces = cls.getInterfaces();
			for (Class<?> i : interfaces) {
				if (interfacesFound.add(i)) {
					getAllInterfaces(i, interfacesFound);
				}
			}
			cls = cls.getSuperclass();
		}
	}

}
