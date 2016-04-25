package pizza;

import java.util.Arrays;

import pizza.domain.Order;
import pizza.domain.customer.Address;
import pizza.domain.customer.Customer;
import pizza.infrastructure.ApplicationContext;
import pizza.infrastructure.JavaConfigApplicationContext;
import pizza.repository.PizzaRepository;
import pizza.service.OrderService;
import pizza.view.View;
import pizza.view.console.ConsoleView;

public class PizzaApp {
	
	static View view = new ConsoleView();
	
	public static void main(String[] args) throws Exception {
		Customer customer = new Customer(); // = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10"); //$NON-NLS-1$
		customer.setId(1);
//		customer.setAddresses(Arrays.asList(new Address("Kiev", "Chervonoarmiyska", "3", "10")));
		Order order;
		
		ApplicationContext ac = new JavaConfigApplicationContext();
		PizzaRepository pizzaRepository = (PizzaRepository) ac.getBean("pizzaRepository");
		System.out.println(pizzaRepository.getPizzaByID(1));
		
		OrderService orderService = (OrderService) ac.getBean("orderService");
		
//		OrderService orderService = (OrderService) ServiceLocator.getInstance().lookup("orderService");
		order = orderService.placeNewOrder(customer, 1, 2, 3);
		view.printMessage(order.toString());
	}

}
