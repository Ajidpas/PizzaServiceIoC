package pizza.domain.customer;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.springframework.beans.factory.FactoryBean;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@DiscriminatorColumn(name = "DTYPE")
public class Customer implements FactoryBean<Customer>{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "customer_id")
	private int id;
	
	@Version
	private Integer version;
	
	@Column(name = "customer_name")
	private String name;
	
//	@OneToOne(cascade = CascadeType.PERSIST)
	@OneToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
	private List<Address> address;
	
//	@ElementCollection
//	private List<Address> addresses;
	
//	public List<Address> getAddresses() {
//		return addresses;
//	}
//
//	public void setAddresses(List<Address> addresses) {
//		this.addresses = addresses;
//	}
	
//	private AccumulativeCard accumulativeCard;
	
//	public List<Address> getAddresses() {
//		return addresses;
//	}
//
//	public void setAddresses(List<Address> addresses) {
//		this.addresses = addresses;
//	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}

	public Customer() {}
	
	public Customer(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
//	public Customer(int id, String name, Address address) {
//		this(id, name);
//		this.address = address;
//	}
//	
//	public Customer(int id, String name, String city, String street, 
//			String house, String flat) {
//		this(id, name);
//		this.address = new Address(city, street, house, flat);
//	}

//	public AccumulativeCard getAccumulativeCard() {
//		return accumulativeCard;
//	}
//
//	public void setAccumulativeCard(AccumulativeCard accumulativeCard) {
//		this.accumulativeCard = accumulativeCard;
//	}

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
