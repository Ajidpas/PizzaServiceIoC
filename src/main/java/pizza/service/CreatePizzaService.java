package pizza.service;

import pizza.domain.Pizza;
import pizza.domain.Pizza.PizzaType;

public class CreatePizzaService {
	
	public Pizza createPizza(Integer id, String name, Double price, PizzaType type) {
		return new Pizza(id, name, price, type);
	}

}
