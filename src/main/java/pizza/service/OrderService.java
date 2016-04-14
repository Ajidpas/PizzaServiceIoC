package pizza.service;

import pizza.domain.Order;
import pizza.domain.customer.Customer;

public interface OrderService {

	Order placeNewOrder(Customer customer, Integer... pizzasID);

}