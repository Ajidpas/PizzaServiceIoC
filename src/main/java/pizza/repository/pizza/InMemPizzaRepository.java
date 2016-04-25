package pizza.repository.pizza;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pizza.domain.Pizza;
import pizza.infrastructure.annotations.BenchMark;
import pizza.repository.PizzaRepository;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;

//@Repository
public class InMemPizzaRepository implements PizzaRepository {

	public List<Pizza> allPizzas = new ArrayList<Pizza>();

	public void setPizzas(List<Pizza> allPizzas) {
		this.allPizzas = allPizzas;
	}

//	public InMemPizzaRepository() {
//		allPizzas = new ArrayList<Pizza>();
//	}

	@BenchMark(active = true)
	public Pizza getPizzaByID(int id) throws NoSuchPizzaException {
		for (Pizza pizza : allPizzas) {
			if (pizza.getId() == id) {
				return pizza;
			}
		}
		throw new NoSuchPizzaException();
	}

//	@PostConstruct
	@Autowired
	public void cookPizzas() {
		allPizzas.add(new Pizza(1, "BigPizza", 1.0, Pizza.PizzaType.MEAT)); //$NON-NLS-1$
		allPizzas.add(new Pizza(2, "SmallPizza", 10.0, Pizza.PizzaType.VEGETARIAN)); //$NON-NLS-1$
		allPizzas.add(new Pizza(3, "MediumPizza", 20.0, Pizza.PizzaType.SEA)); //$NON-NLS-1$
		allPizzas.add(new Pizza(4, "HugePizza", 40.0, Pizza.PizzaType.VGETABLES)); //$NON-NLS-1$
	}

	public int insertPizza(Pizza pizza) {
		allPizzas.add(pizza); //$NON-NLS-1$
		return pizza.getId();
	}

	public int updatePizza(int pizzaId, Pizza pizza) {
		for (Pizza currPizza : allPizzas) {
			if (currPizza.getId() == pizzaId) {
				currPizza.setName(pizza.getName());
				currPizza.setPrice(pizza.getPrice());
				currPizza.setType(pizza.getType());
				return currPizza.getId();
			}
		}
		return -1;
	}

	public int deletePizza(Pizza pizza) {
		Iterator<Pizza> it = allPizzas.iterator();
		while (it.hasNext()) {
			Pizza currPizza = it.next();
			int pizzaId = currPizza.getId();
			if (pizzaId == pizza.getId()) {
				it.remove();
				return pizzaId;
			}
		}
		return -1;
	}

	public List<Pizza> getAllPizzas() {
		return allPizzas;
	}

}
