package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Account;
import model.Customer;

@Stateless
public class Branch implements BranchLocal {

	@PersistenceContext
	EntityManager em;

	public Branch() {
	}

	@Override
	public int createAccount(String cf, double a) throws Exception {
		Customer c = getCustomer(cf);
		if (c != null) {
			Account account = new Account(c, a);
			em.persist(account);
			return account.getAccountId();
		}
		throw new Exception();
	}

	@Override
	public Account getAccount(int num) {

		return em.find(Account.class, num);
	}

	@Override
	public List<Account> getAccounts(String cf) {
		// return List<Account> accounts=em.createNamedQuery("Account.findAllCustomerAccounts",Account.class).setParameter("CF", cf).getResultList();

		Customer c = getCustomer(cf);
		return c.getAccounts();

	}

	@Override
	public void deposit(int num, double a) throws Exception {
		Account ac = getAccount(num);
		if (ac != null) {
			ac.deposit(a);
		} else
			throw new Exception();

	}

	@Override
	public void withdraw(int num, double a) throws Exception {
		Account ac = getAccount(num);
		if (ac != null) {
			ac.withdraw(a);
		} else
			throw new Exception();
	}

	@Override
	public double totalAmount() {
		List<Account> accounts = em.createNamedQuery("Account.findAllCustomerAccounts", Account.class).getResultList();
		double total = 0;
		for (int i = 0; i < accounts.size(); i++) {
			total += accounts.get(i).getBalance();
		}
		return total;
	}

	@Override
	public void createCustomer(String cf, String fn, String ln) throws Exception {
		Customer c = new Customer(cf, fn, ln);
		em.persist(c);

	}

	@Override
	public Customer getCustomer(String cf) {
		return em.find(Customer.class, cf);
	}

}
