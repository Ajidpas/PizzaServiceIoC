package pizza.infrastructure;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class JavaConfigApplicationContext implements ApplicationContext {
	
	private Config config = new JavaConfig();
	
	private final Map<String,Object> context = new HashMap<String, Object>(); // cache
	
	public Object getBean(String beanName) throws Exception {
		if (context.containsKey(beanName)) {
			return context.get(beanName);
		}
		Class<?> clazz = config.getImplementation(beanName);
		if (clazz == null) {
			throw new RuntimeException("Bean not found!");
		}
		
		Object bean;
		
//		Object bean = createBean(clazz);
		
		BeanBuilder builder = new BeanBuilder(clazz, this);
		builder.createBean();
		builder.createBeanProxy(); // 
		builder.callPostConstructMethod();
		builder.callInitMethod();  // - call init() like previous
		bean = builder.build();
		
		context.put(beanName,  bean);
		return bean;
	}
	
	public class BeanBuilder {
		
		private ApplicationContext context;
		
		private final Class<?> clazz;
		
		private Object bean;
		
		public BeanBuilder(Class clazz, JavaConfigApplicationContext context) {
			this.clazz = clazz;
			this.context = context;
		}
		
		public void createBeanProxy() {
//			Proxy.newProxyInstance(loader, interfaces, h);
			
		}

		public void callInitMethod() {
			// TODO Auto-generated method stub
			
		}

		public Object build() {
			return bean;
		}

		public void callPostConstructMethod() throws Exception {
			Method[] methods = clazz.getMethods();
			for (Method m : methods) {
				if (m.getAnnotation(PostConstruct.class) != null) {
					m.invoke(bean);
				}
			}
		}

		private void createBean() throws InstantiationException, IllegalAccessException, InvocationTargetException, Exception {
//			Object bean = null;
			Constructor<?> constructor = clazz.getConstructors()[0];
			Class<?>[] paramTypes = constructor.getParameterTypes();
			if (paramTypes.length == 0) {
				bean = clazz.newInstance();
			} else {
				bean = createInstanceWithParams(constructor, paramTypes);
			}
//			return bean;
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
				paramBeans[i] = context.getBean(paramName); // exception occurs on this line
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
		
		private Object getBean() {
			return bean;
		}
		
	}
}
