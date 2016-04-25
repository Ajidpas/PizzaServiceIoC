package pizza.service.simple;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pizza.domain.Order;
import pizza.domain.customer.Customer;
import pizza.service.OrderService;

//@ContextConfiguration (locations = {"classpath:/appContextChild.xml", "classpath:/repositoryTest.xml"}, inheritInitializers = true) // by default inheritInitializers is true
public class SimpleOrderServiceTest extends RepositoryTestConfig {
	
//	private static final Customer CUSTOMER = new Customer(1, "Vasya", "Kiev", "Chervonoarmiyska", "3", "10"); //$NON-NLS-1$
	
	@Autowired
	private OrderService orderService;
	
	@Test
	public void testPlaceNewOrder() {
//		Integer[] pizzasId = new Integer[]{8};
////		SimpleOrderService orderSertvice = null;
//		Order expected = null;
//		Order result = orderService.placeNewOrder(CUSTOMER, pizzasId);
////		assertEquals(expected, result);
//		System.out.println(result);
	}
	
//	@Test
//	public void placeNewOrderTest() {
//		SimpleOrderService service = new SimpleOrderService();
//		Order expected = null;
//		Order result = service.placeNewOrder(CUSTOMER, -1);
//		assertEquals(expected, result);
//	}

}
