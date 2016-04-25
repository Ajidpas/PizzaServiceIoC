package pizza.dao;

import java.util.List;

import pizza.domain.Pizza;

public interface PizzasDao {
	
	List<Pizza> readPizzas();
	
	int createPizza();
	
	int update();
	
	int delete();

}
