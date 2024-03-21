package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

@Entity
@NamedQueries({
@NamedQuery(name = "findAllPositiveAmounts", query = "SELECT a from Account a WHERE a.balance>0"),
@NamedQuery(name = "Account.findAllCustomerAccounts", query = "SELECT a from Account a WHERE a.customer.CF= :CF")
})
public class Account implements Serializable {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id private int accountId;
	private double balance;
	@ManyToOne
	//@JoinColumn(name="customerFK")
	private Customer customer;
	private static final long serialVersionUID = 1L;

	public Account() {
		super();
	}

	public Account(Customer c, double a) {
		customer=c;
		balance=a;
	}
	
	public Account(double balance) {
		super();
		this.balance = balance;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void withdraw(double w) {
		balance -= w;
	}

	public void deposit(double d) {
		balance += d;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@PrePersist
	public void beforePersist() {
		System.out.println("prepersist");
	}

	@PostPersist
	public void afterPersist() {
		System.out.println("afterPersist");
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", balance=" + balance + ", customer=" + customer + "]";
	}

}
