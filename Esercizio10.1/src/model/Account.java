package model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

@Entity
@NamedQuery(name="findAllPositiveAmount", query = "SELECT a from Account a WHERE a.balance > 0")
@NamedQuery(name ="getTotalAmount", query = "SELECT SUM(a.balance) from Account a")
public class Account implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4146113673811943772L;
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id private int accountId;
	private double balance;
	
	public Account() {
		super();
	}
	
	public Account(double bal) {
		balance = bal;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public void withdraw(double w) {
		balance-=w;
	}
	public void deposit(double d) {
		balance+=d;
	}
	
	@PrePersist
	public void beforePersist() {
		System.out.println("PrePersist");
	}
	
	@PostPersist
	public void afterPersist() {
		System.out.println("PostPersist");
	}
}
