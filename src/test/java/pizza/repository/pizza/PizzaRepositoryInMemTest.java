package pizza.repository.pizza;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pizza.domain.Pizza;
import pizza.domain.Pizza.PizzaType;
import pizza.repository.PizzaRepository;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration (locations = {"classpath:/repositoryTestContext.xml"})
//@ActiveProfiles("dev")
//@Rollback(true)
public class PizzaRepositoryInMemTest extends AbstractTransactionalJUnit4SpringContextTests {
	
	@Autowired
	private PizzaRepository pizzaRepository;
	
	@Test
	public void testGetPizzaById() throws NoSuchPizzaException {
		Pizza pizza = pizzaRepository.getPizzaByID(1);
		System.out.println(pizza);
	}
	
	@Test
	public void testCreate() throws NoSuchPizzaException {
		Pizza pizza = new Pizza();
		pizza.setName("Transactional Pizza insert");
		pizza.setPrice(564);
		pizza.setType(PizzaType.MEAT);
		int id = pizzaRepository.insertPizza(pizza);
		assertNotNull(pizzaRepository.getPizzaByID(id));
	}
	
	@Test
	public void testGetPizzaById2() throws NoSuchPizzaException {
//		final String sql = "INSERT INTO pizza (name, price, pizza_type) values ('sql pizza', 1354, 1)";
//		KeyHolder keyHolder = new GeneratedKeyHolder();
//		
//		jdbcTemplate.update(new PreparedStatementCreator() {
//
//			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
//				return con.prepareStatement(sql);
//			}
//			
//		}, keyHolder);
//		
//		int id = keyHolder.getKey().intValue();
//		
//		Pizza pizza = pizzaRepository.getPizzaByID(id);
//		System.out.println(pizza);
	}

}
