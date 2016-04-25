package pizza.service;

import java.util.List;

import pizza.domain.Pizza;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;

public interface PizzaService {
	
	List<Pizza> getAllPizzas();
	
	Pizza getPizzaBiId(int pizzaId) throws NoSuchPizzaException;
	
	int updatePizza(Pizza pizza);
	
	int deletePizza(Pizza pizza);
	
	int insertPizza(Pizza pizza);

}
