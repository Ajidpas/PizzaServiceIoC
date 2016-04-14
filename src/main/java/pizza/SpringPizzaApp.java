package pizza;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pizza.domain.Order;
import pizza.domain.customer.Customer;
import pizza.repository.PizzaRepository;
import pizza.repository.order.InMemOrderRepository;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.OrderService;

public class SpringPizzaApp {

	public static void main(String[] args) throws NoSuchPizzaException {
		
		ConfigurableApplicationContext repositoryContext = new ClassPathXmlApplicationContext("repository.xml"); // parent for appContext
		ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}, repositoryContext);		
		Customer customer = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10");

		OrderService orderService = (OrderService) appContext.getBean("orderService");
		
		Order order = orderService.placeNewOrder(customer, 10);
		System.out.println(order.toString());

		appContext.close();
		repositoryContext.close();
	}

}
