package pizza;

import java.util.Arrays;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import pizza.domain.Pizza;
import pizza.domain.Pizza.PizzaType;
import pizza.domain.customer.Address;
import pizza.domain.customer.Customer;
import pizza.domain.customer.RegistratedCustomer;
import pizza.infrastructure.state.State;

public class JpaWithoutSpringApp {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();

		Pizza pizza = new Pizza();
//		pizza.setId(25);
		pizza.setName("Margo2");
		pizza.setPrice(120.3);
		pizza.setType(PizzaType.SEA);
		
		Address address = new Address();
		address.setCity("Kiev");
		address.setFlat("10");
		address.setHouse("5");
		address.setStreet("Kudriashova");
		address.setState(new State("Created"));
		
//		address.setPhones(Arrays.asList("123456789"));
		

		Customer customer = new Customer();
		customer.setName("Oleg");
//		address.setPhones(Arrays.asList("123456789", "321654987"));
//		customer.setAddresses(Arrays.asList(address)); // -----------------------
		customer.setAddress(Arrays.asList(address));
		address.setCustomer(customer);
//		address.setCustomer(customer);
//		customer.setId(1);
//		customer.setAddresses(Arrays.asList(address));
//		customer.setAddresses(Arrays.asList(address, address));
		
//		Customer regCustomer = new RegistratedCustomer();
//		regCustomer.setAddress(address);
		
		try {
			em.getTransaction().begin();
//			em.persist(pizza);
//			em.persist(address);
			em.persist(customer);
//			customer.setName("Nick");
//			em.persist(regCustomer);
//			em.persist(address);
			em.getTransaction().commit();
			em.clear();
			System.out.println("address = " + address.getCustomer());
			
			Address readAddress = em.find(Address.class, address.getId());
			System.out.println(readAddress.getCustomer());
			
//			Pizza p = em.find(Pizza.class, 25);
//			System.out.println(p);
			
			// version
//			Customer customer1 = em.find(Customer.class, customer.getId());
//			Scanner scanner = new Scanner(System.in);
//			scanner.next(); // go to data base and change version variable value. then come back, type any character, press enter and here we have OptimisticLockException
//			
//			em.getTransaction().begin();
//			customer.setName("Nick");
//			em.getTransaction().commit();
////			System.out.println(customer.getAddresses().get(0).getState().state);
//			System.out.println(customer.getAddress().getState().state);
			// version
		} finally {
			em.close();
			emf.close();
		}
	}

}
