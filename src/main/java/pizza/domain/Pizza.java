package pizza.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;

@Entity
public class Pizza {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
//	@SequenceGenerator(name = "pizza_sequence")
	private Integer id;

	private String name;

	private double price;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "pizza_type")
	private PizzaType type;
	
	public Pizza() {}

	public Pizza(int id, String name, double price, PizzaType type) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.type = type;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public PizzaType getType() {
		return type;
	}

	public void setType(PizzaType type) {
		this.type = type;
	}
	
	public String toString() {
		return "Pizza: [id = " + id + ", name = " + name + ", price = " + price + ", type = " + type + "]";
	}
	
	public static enum PizzaType {
		VEGETARIAN,
		SEA,
		MEAT,
		VGETABLES
	}
	
}
