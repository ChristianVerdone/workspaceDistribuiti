package presentation;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			float euro= Float.parseFloat(request.getParameter("euro"));
			float dollars = converter.converti(euro);
			response.getWriter().append(euro+" Euro sono "+ dollars+" dollari.").flush();;
		}catch(EJBException e) {
			response.getWriter().append("non sei autorizzzto per questa operazione");
		}catch (Exception e) {
			response.getWriter().append("formato numerico errato o hai dimenticato di inserire...");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
