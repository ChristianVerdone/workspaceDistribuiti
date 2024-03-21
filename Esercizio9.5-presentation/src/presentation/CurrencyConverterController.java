package presentation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import application.CurrencyConverterRemote;


/**
 * Servlet implementation class CurrencyConverterController
 */
@WebServlet("/CurrencyConverterController")
public class CurrencyConverterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CurrencyConverterRemote converter;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CurrencyConverterController() {
        super();
        try {
        	Properties jndiProps = new Properties();
        	jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");//TODO
        	jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8088");
        	jndiProps.put("org.jboss.naming.client.ejb.context", true);
        	
        	Context initialContext = new InitialContext(jndiProps);
        	converter = (CurrencyConverterRemote) initialContext.lookup("Esercizio9.5-application/CurrencyConverter!application.CurrencyConverterRemote");
        }catch(Exception e) {
        	//e.printStackTrace(e);
        }
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (converter !=null) {
			PrintWriter pw = response.getWriter();
			try {
				float euro = Float.parseFloat(request.getParameter("euro"));
				float dollars = converter.convert(euro);
				pw.append(euro + " Euro sono: " + dollars + " dollari");
				response.getWriter().append("Served at: ").append(request.getContextPath());
				pw.flush();
			}catch(Exception  e) {
				e.printStackTrace();
			}
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
