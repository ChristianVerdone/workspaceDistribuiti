package controller;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.transaction.UserTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import model.Account;
import service.BranchLocal;

@Produces({ "application/json", "application/x-www-form-urlencoded" })
@Consumes({ "application/json", "application/x-www-form-urlencoded" })
@Path("/bank")
public class BankControllerServiceImpl implements BankControllerService {

	@EJB(lookup = "java:global/Esercizio10.5/Branch!service.BranchLocal")
	private BranchLocal branch;

	@Resource
	UserTransaction utx;

	@Override
	public Response createAccount(String cf, double amount) {
		try {
			int accountNumber = branch.createAccount(cf, amount);
			return Response.ok().entity("Creato conto per l'utente[cf: " + cf + "] con numero: " + accountNumber)
					.build();
		} catch (Exception e) {
			e.printStackTrace();
			//return Response.status(500, "Errore nella creazione del conto").build();
			return Response.serverError().entity("Errore nella creazione dell'utente").build();
		}
	}

	@Override
	public Response deposit(int accountNumber, double amount) throws Exception {
		try {
			branch.deposit(accountNumber, amount);
			return Response.ok().entity("Depositato: "+ amount+ " sul Conto Numero: "+accountNumber).build(); 
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().entity("Errore nel deposito").build();
		}
	}

	@Override
	public Response withdraw(int accountNumber, double amount) throws Exception {
		try {
			branch.withdraw(accountNumber, amount);
			return Response.ok().entity("Prelevato: "+ amount+ " sul Conto Numero: "+accountNumber).build(); 
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().entity("Errore nel prelievo").build();
		}
	}

	@Override
	public Response createCustomer(String cf, String fn, String ln) {
		System.out.println("create customer: " + cf);
		System.out.println("branch: " + branch);
		try {
			branch.createCustomer(cf, fn, ln);
			return Response.ok().entity("Creato Cliente [cf: " + cf + "]").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500, "Errore nella creazione del conto").build();
		}
	}

	@Override
	public Response allCustomerAccounts(String cf) {
		StringBuilder sb = new StringBuilder("Conti di [cf: " + cf + "]\n");
		List<Account> accounts = branch.getAccounts(cf);
		if (!accounts.isEmpty()) {
			for (Account a : accounts) {
				sb.append(a.toString() + "\n");
			}
		} else {
			sb.append("Nessun Conto");
		}
		return Response.ok().entity(sb.toString()).build();
	}

	@Override
	public Response transfer(int accountNumber, int otherAccountNumber, double amount) {
		try {
			utx.begin();
			branch.withdraw(accountNumber, amount);
			branch.deposit(otherAccountNumber, amount);
			utx.commit();
			return Response.ok().entity("Trasferimento di " + amount + " euro dal conto " + accountNumber + " al conto "
					+ otherAccountNumber).build();
		} catch (Exception e) {
			try {
				utx.rollback();
				e.printStackTrace();
				return Response.serverError().entity("Trasferimento non possibile").build();
			} catch (Exception ee) {
				return Response.serverError().entity("Rollback non possibile").build();
			}
		}
	}

}
