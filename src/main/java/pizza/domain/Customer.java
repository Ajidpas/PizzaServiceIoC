package pizza.domain;

import org.springframework.beans.factory.FactoryBean;


public class Customer implements FactoryBean<Customer>{
	
	private int id;
	
	private String name;
	
	private Address address;
	
	private AccumulativeCard accumulativeCard;
	
	public Customer(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Customer(int id, String name, Address address) {
		this(id, name);
		this.address = address;
	}
	
	public Customer(int id, String name, String city, String street, 
			String house, String flat) {
		this(id, name);
		this.address = new Address(city, street, house, flat);
	}

	public AccumulativeCard getAccumulativeCard() {
		return accumulativeCard;
	}

	public void setAccumulativeCard(AccumulativeCard accumulativeCard) {
		this.accumulativeCard = accumulativeCard;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + "]"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	public Customer getObject() throws Exception {
		return new Customer(1, "Abc");
	}

	public Class<?> getObjectType() {
		return Customer.class;
	}

	public boolean isSingleton() {
		return false;
	}
	
	

}
