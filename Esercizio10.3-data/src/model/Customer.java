package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {
	@Id
	private String CF;
	private String firstName;
	private String lastName;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Account> accounts;
	private static final long serialVersionUID = 1L;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String cF, String firstName, String lastName) {
		super();
		CF = cF;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getCF() {
		return CF;
	}

	public void setCF(String cF) {
		CF = cF;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<Account> getAccounts() {
		accounts.size();
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "Customer [CF=" + CF + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	

}
