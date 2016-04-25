package pizza.infrastructure.benchmarkproxy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ClassUtils;

public class BenchMarkProxyBeanPostProcessor implements BeanPostProcessor {

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//		System.out.println(ClassUtils.isCglibProxyClass(bean.getClass()) + " bean is - " + beanName);
		// TODO: check is bean is CGLIB proxy and accomplish availability to annotation under createOrder method
		return new BenchMarkProxyBuilder().createProxyForBean(bean);
	}

	public Object postProcessBeforeInitialization(Object arg0, String arg1) throws BeansException {
		return arg0;
	}

}
