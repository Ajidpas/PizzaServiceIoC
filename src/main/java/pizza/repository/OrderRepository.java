package pizza.repository;

import pizza.domain.Order;
import pizza.infrastructure.annotations.BanchMark;

public interface OrderRepository {

	@BanchMark(active = true)
	Long saveOrder(Order newOrder);
	
}
