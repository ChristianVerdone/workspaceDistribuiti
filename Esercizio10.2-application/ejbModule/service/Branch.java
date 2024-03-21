package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Account;


@Stateless
public class Branch implements BranchLocal,BranchRemote {

	@PersistenceContext
	EntityManager em;
	
	public Branch() {
	}
	
	@Override
	public double totalAmount() {
		List<Account>accounts=em.createNamedQuery("findAllPositiveAmounts", Account.class).getResultList();
		double total=0;
		for(int i=0;i<accounts.size();i++) {
			total+=accounts.get(i).getBalance();
		}
		return total;
	}

	@Override
	public double totalAmountQ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int createAccount() {
		Account a = new Account(0);
		em.persist(a);
		return a.getAccountId();
		//System.out.println("createAccount");
	}

	@Override
	public void deposit(int accountId, double amount) {
		Account a = em.find(Account.class, accountId);
		a.deposit(amount);		
	}

}
