package pizza.domain.customer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

import pizza.infrastructure.state.State;
import pizza.infrastructure.state.StateConverter;

@Entity
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@Column(name = "ADDR_STATE")
	@Convert(converter = StateConverter.class)
	private State state;
	
//	@OneToOne(mappedBy = "address") // field in the customer CLASS (not table in the database)
	@ManyToOne(optional = false)
	@JoinTable(name = "customer_address")
	private Customer customer;
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	//	@Type(type = "org.hibernate.type.TextType")
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
//	@ElementCollection()
//	private List<String> phones;
//	
//	public List<String> getPhones() {
//		return phones;
//	}
//
//	public void setPhones(List<String> phones) {
//		this.phones = phones;
//	}

	private String city;
	
	private String street;
	
	private String house;
	
	private String flat;

	public Address() {}
	
	public Address(String city, String street, String house, String flat) {
		super();
		this.city = city;
		this.street = street;
		this.house = house;
		this.flat = flat;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getFlat() {
		return flat;
	}

	public void setFlat(String flat) {
		this.flat = flat;
	}

}
