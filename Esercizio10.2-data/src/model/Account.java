package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Account
 *
 */
@Entity
@NamedQuery(name = "findAllPositiveAmounts", query = "SELECT a from Account a WHERE a.balance > 0")
public class Account implements Serializable {

	private double balance;
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private int accountId;
	private static final long serialVersionUID = 1L;

	public Account() {
		super();
	}

	public Account(double balance) {
		this.balance = balance;
	}

	public double getBalance() {
		return this.balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAccountId() {
		return this.accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public void withdraw(double w) {
		balance -= w;
	}

	public void deposit(double d) {
		balance += d;
	}
}
