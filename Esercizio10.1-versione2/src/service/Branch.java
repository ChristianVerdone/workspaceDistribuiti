package service;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Account;

@Stateless
@PersistenceContext(name = "Esercizio10.1-versione2")
public class Branch implements BranchLocal {

	@Resource SessionContext ctx;
	
	public Branch() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double totalAmount() {
		EntityManager em=(EntityManager)ctx.lookup("Esercizio10.1-versione2");
		List<Account> accounts = em.createNamedQuery("findAllPositiveAmount", Account.class).getResultList();
		double total = 0;
		for (int i = 0; i < accounts.size(); i++) {
			total += accounts.get(i).getBalance();
		}
		return total;
	}

	@Override
	public double totalAmountQ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void createAccount(double a) {
		EntityManager em=(EntityManager)ctx.lookup("Esercizio10.1-versione2");
		Account account = new Account(a);
		em.persist(account);
		// System.out.println("CreateAccount");
	}
}
