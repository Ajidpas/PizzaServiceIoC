package pizza;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pizza.domain.Customer;
import pizza.domain.Order;
import pizza.domain.Pizza;
import pizza.repository.PizzaRepository;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.OrderService;

public class SpringPizzaApp {

	public static void main(String[] args) throws NoSuchPizzaException {
		
		ConfigurableApplicationContext repositoryContext = new ClassPathXmlApplicationContext("repository.xml"); // parent for appContext
		ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}, false); // he knows about beans in the parent application context
		appContext.setParent(repositoryContext);
		appContext.refresh();
		
		System.out.println("Success" + appContext.getApplicationName());
		PizzaRepository pizzaRepository = (PizzaRepository) appContext.getBean("pizzaRepository");
		System.out.println(pizzaRepository.getPizzaByID(10));
		
		Customer customer = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10");

		OrderService orderService = (OrderService) appContext.getBean("orderService");
		Order order = orderService.placeNewOrder(customer, 10);
		System.out.println(order.toString());
		
		// 8.04.16
		Pizza pizza = appContext.getBean(Pizza.class);
		System.out.println(pizza);
		
		Customer customerFromAppContext = (Customer) appContext.getBean("customer");
		Customer customerFromRepository = (Customer) repositoryContext.getBean("customer");
		Customer customerByClass = appContext.getBean(Customer.class);
		System.out.println("customerFromAppContext: " + customerFromAppContext);
		System.out.println("customerFromRepository: " + customerFromRepository);
		System.out.println("customerByClass: " + customerByClass);

		appContext.close();
		repositoryContext.close();
	}

}
