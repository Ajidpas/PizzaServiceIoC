package pizza.infrastructure;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

import pizza.infrastructure.annotations.BanchMark;
import pizza.infrastructure.annotations.InitInvoking;
import pizza.infrastructure.annotations.PostConstruct;

public class BeanBuilder {
	
	private ApplicationContext applicationContext;
	
	private final Class<?> clazz;
	
	private Object bean;
	
	private Object beanProxy;
	
	public BeanBuilder(Class clazz, JavaConfigApplicationContext applicationContext) {
		this.clazz = clazz;
		this.applicationContext = applicationContext;
	}
	
	public void createBeanProxy() {
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			if (method.getAnnotation(BanchMark.class) != null) {
				BanchMark banchMark = method.getAnnotation(BanchMark.class);
				boolean active = banchMark.active();
				if (active) {
					createProxyForBean();
					return;
				}
			}
		}
	}

	private void createProxyForBean() {
		Class beanClass = bean.getClass();
		List<Class<?>> interfaceList = getAllInterfaces(beanClass);
		Class[] interfaces = (interfaceList.toArray(new Class[interfaceList.size()]));
		InvocationHandler handler = new MyInvocationHandler(bean);
		beanProxy = Proxy.newProxyInstance(beanClass.getClassLoader(), interfaces, handler);
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
	
	public void callPostConstructMethod() throws Exception {
		Method[] methods = clazz.getMethods();
		for (Method m : methods) {
			if (m.getAnnotation(PostConstruct.class) != null) {
				m.invoke(bean);
			}
		}
	}

	public void callInitMethod() throws Exception {
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			if (method.getAnnotation(InitInvoking.class) != null) {
				method.invoke(bean);
			}
		}
	}

	public Object build() {
		if (beanProxy != null) {
			return beanProxy;
		}
		return bean;
	}

	public void createBean() throws InstantiationException, IllegalAccessException, InvocationTargetException, Exception {
		Constructor<?> constructor = clazz.getConstructors()[0];
		Class<?>[] paramTypes = constructor.getParameterTypes();
		if (paramTypes.length == 0) {
			bean = clazz.newInstance();
		} else {
			bean = createInstanceWithParams(constructor, paramTypes);
		}
	}

	private Object createInstanceWithParams(Constructor<?> constructor, Class<?>[] paramTypes)
			throws Exception, InstantiationException, IllegalAccessException, InvocationTargetException {
		Object bean;
		Object[] paramBeans = getParams(paramTypes);
		bean = constructor.newInstance(paramBeans);
		return bean;
	}
	
	private Object[] getParams(Class<?>[] paramTypes) throws Exception {
		Object[] paramBeans = new Object[paramTypes.length];
		for (int i = 0; i < paramTypes.length; i++) {
			String paramName = getBeanNameByType(paramTypes[i]);
			paramBeans[i] = applicationContext.getBean(paramName); // exception occurs on this line
		}
		return paramBeans;
	}

	private String getBeanNameByType(Class<?> paramType) {
		String paramTypeName = paramType.getSimpleName(); // ---------------------------------------
		String paramName = getFirstLetterToLowerCase(paramTypeName);
		return paramName;
	}

	private String getFirstLetterToLowerCase(String paramTypeName) {
		String paramName = Character.toLowerCase(paramTypeName.charAt(0)) + paramTypeName.substring(1);
		return paramName;
	}
	
}
