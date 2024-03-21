package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Account;


@Stateless
public class Branch implements BranchLocal{

	@PersistenceContext
	EntityManager em;
	
	public Branch() {
		// TODO Auto-generated constructor stub
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED) //REQUIRES_NEW
	@Override
	public double totalAmount() {
		List<Account> accounts = em.createNamedQuery("findAllPositiveAmount",Account.class).getResultList();
		double total = 0;
		for(int i =0; i<accounts.size(); i++) {
			total += accounts.get(i).getBalance();
		}
		return total;
	}

	
	@Override
	public double totalAmountQ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED) //REQUIRES_NEW/ SUPPORTS/MANDATORY 
	public int createAccount() {
		Account account = new Account();
		em.persist(account);
		
		return account.getAccountId();
		//System.out.println("CreateAccount");
	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)//REQUIRES_NEW
	@Override
	public void deposit(int num, double a) throws Exception {
		Account ac = em.find(Account.class, num);
		if (ac != null) {
			ac.deposit(a);
		} else
			throw new Exception();

	}
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void withdraw(int num, double a) throws Exception {
		Account ac = em.find(Account.class, num);
		if (ac != null) {
			ac.withdraw(a);
		} else
			throw new Exception();
	}

	
}
