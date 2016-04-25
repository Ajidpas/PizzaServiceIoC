package pizza.service.simple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pizza.domain.Pizza;
import pizza.repository.PizzaRepository;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;
import pizza.service.PizzaService;

@Service
public class SimplePizzaService implements PizzaService {
	
	@Autowired
	private PizzaRepository pizzaRepository;

	public Pizza getPizzaBiId(int pizzaId) throws NoSuchPizzaException {
		return pizzaRepository.getPizzaByID(pizzaId);
	}

	public int updatePizza(Pizza pizza) {
		return pizzaRepository.updatePizza(pizza.getId(), pizza);
	}

	public int deletePizza(Pizza pizza) {
		return pizzaRepository.deletePizza(pizza);
	}

	public int insertPizza(Pizza pizza) {
		return pizzaRepository.insertPizza(pizza);
	}

	public List<Pizza> getAllPizzas() {
		return pizzaRepository.getAllPizzas();
	}

}
