package presentation;

import java.io.IOException;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.CurrencyConverterLocal;

/**
 * Servlet implementation class ConverterController
 */
@WebServlet("/ConverterController")
@DeclareRoles({ "user", "employer", "admin" })
@RunAs("admin")
public class ConverterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private CurrencyConverterLocal converter;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConverterController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			float euro = Float.parseFloat(request.getParameter("euro"));
			float dollars = converter.converti(euro);
			response.getWriter().append(euro + " Euro sono: " + dollars + " dollari").flush();
		} catch (EJBAccessException e) {
			response.getWriter().append("Non sei autorizzato per questa operazione");
		} catch (Exception e) {
			response.getWriter().append("Formato numerico errato o hai dimenticat di inserire...");
		}

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
