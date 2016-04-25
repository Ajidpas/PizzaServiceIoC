package pizza.repository.pizza;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import pizza.domain.Pizza;
import pizza.domain.Pizza.PizzaType;
import pizza.repository.PizzaRepository;
import pizza.repository.pizza.exceptions.NoSuchPizzaException;

@Repository
public class DaoPizzaRepository implements PizzaRepository {
	
	private static final String SELECT_PIZZA_BY_ID = 
			"SELECT pizza_id, pizza_name, pizza_price, pizza_type FROM pizzas WHERE pizza_id = ?";

	private static final String INSERT_PIZZA = 
			"INSERT INTO pizzas (pizza_name, pizza_price, pizza_type) VALUES (?, ?, CAST(? AS pizza_type_enum))";
	
	private static final String UPDATE_PIZZA = 
			"UPDATE pizzas SET pizza_name = ?, pizza_price = ?, pizza_type = CAST(? AS pizza_type_enum) WHERE pizza_id = ?";

	private static final String DELETE = 
			"DELETE FROM pizzas WHERE pizza_id = ?";
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<Pizza> getAllPizzas() {
		return jdbcTemplate.queryForObject("SELECT * FROM pizzas", new RowMapper<List<Pizza>>() {

			public List<Pizza> mapRow(ResultSet rs, int rowNum) throws SQLException {
				List<Pizza> pizzas = new ArrayList<Pizza>();
				do {
					Pizza pizza = getPizzaFromResultSet(rs);
					pizzas.add(pizza);
				} while (rs.next());
				return pizzas;
			}
			
		});
	}
	
	private Pizza getPizzaFromResultSet(ResultSet rs) throws SQLException {
		int id = rs.getInt("pizza_id");
		String name = rs.getString("pizza_name");
		double price = rs.getDouble("pizza_price");
		PizzaType type = PizzaType.valueOf(rs.getString("pizza_type").toUpperCase());
		return new Pizza(id, name, price, type);
	}

	public Pizza getPizzaByID(int id) throws NoSuchPizzaException {
		return jdbcTemplate.queryForObject(SELECT_PIZZA_BY_ID, 
				new Object[]{id}, 
				new RowMapper<Pizza>() {

					public Pizza mapRow(ResultSet rs, int rowNumber) throws SQLException {
						return getPizzaFromResultSet(rs);
					}
			
				});
	}
	
	public int insertPizza(Pizza pizza) {
		String name = pizza.getName();
		double price = pizza.getPrice();
		String type = pizza.getType().name();
		return jdbcTemplate.update(INSERT_PIZZA, name, price, type);
	}

	public int updatePizza(int pizzaId, Pizza pizza) {
		int id = pizza.getId();
		String name = pizza.getName();
		double price = pizza.getPrice();
		String type = pizza.getType().name();
		return jdbcTemplate.update(UPDATE_PIZZA, name, price, type, id);
	}

	public int deletePizza(Pizza pizza) {
		int id = pizza.getId();
		return jdbcTemplate.update(DELETE, id);
	}

}
