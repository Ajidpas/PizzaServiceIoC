package pizza.domain.customer;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name="registratedCustomer")
public class RegistratedCustomer extends Customer {
	
	@Temporal(value = TemporalType.DATE)
	private Date creationDate;
	
	public RegistratedCustomer() {
		super();
		this.creationDate = new Date();
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
