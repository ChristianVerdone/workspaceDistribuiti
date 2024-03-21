package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;

import model.Account;
import model.Customer;
import service.BranchLocal;

/**
 * Servlet implementation class BankController
 */
@WebServlet("/BankController")
public class BankController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BranchLocal branch;

	@Resource
	UserTransaction utx;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BankController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String operation = request.getParameter("operation");
		PrintWriter printWriter = response.getWriter();
		String cf, fn, ln;
		String message = "";
		int num;
		double amount;

		switch (operation) {
		case "createCustomer":
			cf = request.getParameter("cf");
			fn = request.getParameter("fn");
			ln = request.getParameter("ln");
			try {
				branch.createCustomer(cf, fn, ln);
				message="customer cf= " + cf;
			} catch (Exception e) {
				e.printStackTrace();
				message= "errore creazione utente avente cf: " + cf;
			}
			break;
		case "getCustomer":
			cf = request.getParameter("cf");
			Customer c = branch.getCustomer(cf);
			message="il costumer con cf: "+cf+" è: "+c;
			break;
		case "createAccount":
			cf = request.getParameter("cf");
			amount = Double.parseDouble(request.getParameter("amount"));
			try {
				branch.createAccount(cf, amount);
				message= "createAccount con cf: " + cf + " e amount: " + amount;
			} catch (Exception e) {
				e.printStackTrace();
				message="errore createAccount con cf: " + cf;
			}
			break;
		case "getAccount":
			num = Integer.parseInt(request.getParameter("accountId"));
			Account a = branch.getAccount(num);
			message="l'account con id: "+ num+" è: "+a;
			break;
		case "deposit":
			num = Integer.parseInt(request.getParameter("accountId"));
			amount = Double.parseDouble(request.getParameter("amount"));
			try {
				branch.deposit(num, amount);
				message= "deposit di amount: " + amount + " al conto n: " + num;
			} catch (Exception e) {
				e.printStackTrace();
				message= "errore deposit";
			}
			break;
		case "withdraw":
			num = Integer.parseInt(request.getParameter("accountId"));
			amount = Double.parseDouble(request.getParameter("amount"));
			try {
				branch.withdraw(num, amount);
				message= "withdraw di amount: " + amount + " al conto n: " + num;
			} catch (Exception e) {
				e.printStackTrace();
				message= "errore withdraw";
			}
			break;
		case "allCustomerAccounts":	
			try {
				cf = request.getParameter("cf");
				List<Account> accounts = branch.getAccounts(cf);
				message= "lista di account con cf: " + cf + " è: " + accounts;

			} catch (Exception e) {
				e.printStackTrace();
				message="errore allCustomerAccounts ";
			}
		case "transfer":
			try {
				int num1 = Integer.parseInt(request.getParameter("account1"));
				int num2 =  Integer.parseInt(request.getParameter("account2"));
				amount = Double.parseDouble(request.getParameter("amount"));
				
				utx.begin();
				branch.withdraw(num1, amount);
				branch.deposit(num2, amount);
				utx.commit();
				message="trasferimento di: "+ amount+" euro dal conto "+num1+" al conto "+num2;
			}catch (Exception e) {
				try {
					utx.rollback(); message="trasferimento non possibile";
				}catch (Exception e1) {
					
				}
			}
			break;
		}
		printWriter.println(message);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
