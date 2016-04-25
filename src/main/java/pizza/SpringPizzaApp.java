package pizza;

import java.util.Arrays;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import pizza.domain.Order;
import pizza.domain.Pizza;
import pizza.domain.Pizza.PizzaType;
import pizza.domain.customer.Address;
import pizza.domain.customer.Customer;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.OrderService;
import pizza.service.PizzaService;

public class SpringPizzaApp {

	public static void main(String[] args) throws NoSuchPizzaException {
		
		ConfigurableApplicationContext repositoryContext = new ClassPathXmlApplicationContext("repository.xml"); // parent for appContext
		ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}, repositoryContext);
		ConfigurableEnvironment conficurableEnvironment = appContext.getEnvironment();
		conficurableEnvironment.setActiveProfiles("prod");
//		appContext.setEnvironment(conficurableEnvironment);
		appContext.refresh();
//		Customer customer = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10");
		Customer customer = new Customer(); // = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10"); //$NON-NLS-1$
		customer.setId(1);
//		customer.setAddresses(Arrays.asList(new Address("Kiev", "Chervonoarmiyska", "3", "10")));

		OrderService orderService = appContext.getBean(OrderService.class);
		
		Order order = orderService.placeNewOrder(customer, 4);
		System.out.println(order.toString());
		
		Pizza newPizza = new Pizza(100500, "New pizza", 1000, PizzaType.MEAT);
		PizzaService pizzaService = appContext.getBean(PizzaService.class);
//		System.out.println("Insert one pizza, affected rows number: " + pizzaService.insertPizza(newPizza));
//		System.out.println(pizzaService.getAllPizzas());
		Pizza updatedPizza = new Pizza(7, "Old pizza", 200, PizzaType.SEA);
		System.out.println(pizzaService.updatePizza(updatedPizza));
		
//		Pizza deletingPizza = pizzaService.getPizzaBiId(6);
//		System.out.println(pizzaService.deletePizza(deletingPizza));

		appContext.close();
		repositoryContext.close();
	}

}
