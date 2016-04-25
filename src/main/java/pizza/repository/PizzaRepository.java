package pizza.repository;

import java.util.List;

import pizza.domain.Pizza;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;

public interface PizzaRepository {
	
	List<Pizza> getAllPizzas();
	
	Pizza getPizzaByID(int id) throws NoSuchPizzaException;
	
	int insertPizza(Pizza pizza);
	
	int updatePizza(int pizzaId, Pizza pizza);
	
	int deletePizza(Pizza pizza);

}
