package pizza.service.simple;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Lookup;

import pizza.domain.Order;
import pizza.domain.Pizza;
import pizza.domain.customer.Customer;
import pizza.infrastructure.ServiceLocator;
import pizza.infrastructure.annotations.BenchMark;
import pizza.repository.OrderRepository;
import pizza.repository.PizzaRepository;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.OrderService;

public class SimpleOrderService implements OrderService {
	private Customer customer;
	
//	private ApplicationContext appContext;
	
//	public Order getOrder() {
//		return order;
//	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	/**
	 * This method is called in the end of bean creation by application context 
	 * and he sets himself into the bean
	 */
//	public void setApplicationContext(ApplicationContext appContext) { 
//		this.appContext = appContext;
//	}

	private static final int MIN_NUMBER_OF_PIZZAS = 1;
	
	private static final int MAX_NUMBER_OF_PIZZAS = 10;
	
	private ServiceLocator locator = ServiceLocator.getInstance();

	private PizzaRepository pizzaRepository;

	private OrderRepository orderRepository;
	
	public SimpleOrderService(PizzaRepository pizzaRepository, OrderRepository orderRepository) {
		super();
		this.pizzaRepository = pizzaRepository;
		this.orderRepository = orderRepository;
	}

	@BenchMark(active = true)
	public Order placeNewOrder(Customer customer, Integer ... pizzasID) {
		Order newOrder = createOrder();
		
		if (pizzasID.length >= 1 || pizzasID.length <= 10) {
			List<Pizza> pizzas;
			try {
				pizzas = pizzasByArrOfId(pizzasID);
//				newOrder = createOrder(customer, pizzas);
				newOrder.setCustomer(customer); // -------------------110416
				newOrder.setPizzaList(pizzas); // ---------------------110416
		        orderRepository.saveOrder(newOrder);  // set Order Id and save Order to in-memory list
			} catch (NoSuchPizzaException e) {
				System.out.println(e.toString());
			}
		}
		return newOrder;
	}

	public boolean addPizzaIntoOrder(Order order, Integer ... pizzasID) {
		if (order != null) {
			int orderPizzas = order.getPizzaList().size();
			if (pizzasID.length + orderPizzas <= 10) {
				List<Pizza> pizzas;
				try {
					pizzas = pizzasByArrOfId(pizzasID);
					for (Pizza pizza : pizzas) {
						order.addPizza(pizza);
					}
					return true;
				} catch (NoSuchPizzaException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return false;
	}
	
	public boolean deletePizzaFromOrder(Order order, Integer ... pizzasID) {
		 if (order != null) {
			 int orderPizzas = order.getPizzaList().size();
			 if (orderPizzas - pizzasID.length >= 1) {
				 List<Pizza> pizzas;
				try {
					pizzas = pizzasByArrOfId(pizzasID);
					for (Pizza pizza : pizzas) {
						order.deletePizza(pizza.getId());	 
					}
					return true;
				} catch (NoSuchPizzaException e) {
					e.printStackTrace();
				}
			 }
		 }
		 return false;
	}

	private List<Pizza> pizzasByArrOfId(Integer... pizzasID) throws NoSuchPizzaException {
		List<Pizza> pizzas = new ArrayList<Pizza>();
        for (Integer id : pizzasID) { 
                pizzas.add(pizzaRepository.getPizzaByID(id));  // get Pizza from predifined in-memory list
        }
		return pizzas;
	}

	@Lookup
	protected Order createOrder() {
		return null;
	}

}
