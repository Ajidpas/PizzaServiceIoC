package pizza.repository.order;

import java.util.ArrayList;
import java.util.List;

import pizza.domain.Order;
import pizza.infrastructure.annotations.BenchMark;
import pizza.infrastructure.annotations.InitInvoking;
import pizza.repository.OrderRepository;

public class InMemOrderRepository implements OrderRepository {
	
	private List<Order> orders;

	@BenchMark(active = true)
	public Long saveOrder(Order newOrder) {
		if (orders == null) {
			orders = new ArrayList<Order>();
		}
		orders.add(newOrder);
		return newOrder.getId();
	}
	
	@InitInvoking
	public void init() {
		System.out.println("InMemOrderRepository.init() method was called");
	}

}
