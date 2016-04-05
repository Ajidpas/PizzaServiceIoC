package pizza.repository;

import pizza.domain.Order;
import pizza.infrastructure.annotations.BenchMark;

public interface OrderRepository {

	Long saveOrder(Order newOrder);
	
}
