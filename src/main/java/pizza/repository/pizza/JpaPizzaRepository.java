package pizza.repository.pizza;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pizza.domain.Pizza;
import pizza.repository.PizzaRepository;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;

@Repository("pizzaRepository")
@Transactional
public class JpaPizzaRepository implements PizzaRepository {
	
	@PersistenceContext
	private EntityManager em;
	
//	@Autowired
//	private EntityManagerFactory emf;

	public Pizza getPizzaByID(int id) throws NoSuchPizzaException {
		return em.find(Pizza.class, id);
	}
	
	public List<Pizza> getAllPizzas() {
		// TODO Auto-generated method stub
		return null;
	}

	public int insertPizza(Pizza pizza) {
		System.out.println("inserting pizza");
		em.persist(pizza);
		return pizza.getId();
	}

	public int updatePizza(int pizzaId, Pizza pizza) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deletePizza(Pizza pizza) {
		// TODO Auto-generated method stub
		return 0;
	}

}
