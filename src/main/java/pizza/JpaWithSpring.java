package pizza;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pizza.domain.Pizza;
import pizza.domain.Pizza.PizzaType;
import pizza.repository.PizzaRepository;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;

public class JpaWithSpring {
	
	public static void main(String[] args) throws NoSuchPizzaException {
		ConfigurableApplicationContext repositoryContext = new ClassPathXmlApplicationContext("repositoryMySqlContext.xml"); // parent for appContext
		ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(new String[]{"appContext.xml"}, false);
		appContext.setParent(repositoryContext);
		appContext.refresh();
		
		PizzaRepository pr = (PizzaRepository) appContext.getBean("pizzaRepository");
//		Pizza pizza = pr.getPizzaByID(1);
//		System.out.println(pizza);
		
		Pizza pizzaInsert = new Pizza();
		pizzaInsert.setName("Transactional Pizza insert");
		pizzaInsert.setPrice(564);
		pizzaInsert.setType(PizzaType.MEAT);
		System.out.println("id inserted pizza = " + pr.insertPizza(pizzaInsert));

		appContext.close();
		repositoryContext.close();
	}

}
