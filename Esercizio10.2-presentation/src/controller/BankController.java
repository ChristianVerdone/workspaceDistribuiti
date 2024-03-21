package controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BranchLocal;

/**
 * Servlet implementation class BankController
 */
@WebServlet("/BankController")
public class BankController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       @EJB private BranchLocal branch;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BankController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		double amount= Double.parseDouble(request.getParameter("amount"));
		int id= branch.createAccount();
		branch.deposit(id,amount);
		response.getWriter().print("total amount: "+branch.totalAmount());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
