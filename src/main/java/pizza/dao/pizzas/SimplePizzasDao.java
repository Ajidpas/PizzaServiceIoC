package pizza.dao.pizzas;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pizza.dao.PizzasDao;
import pizza.domain.Pizza;

@Repository
public class SimplePizzasDao implements PizzasDao {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Pizza> readPizzas() {
		// TODO Auto-generated method stub
		return null;
	}

	public int createPizza() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete() {
		// TODO Auto-generated method stub
		return 0;
	}

}
