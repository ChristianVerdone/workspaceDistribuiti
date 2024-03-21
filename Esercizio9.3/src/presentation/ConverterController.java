package presentation;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import application.CurrencyConverterLocal;

@Path("/CurrencyConverter")
@Produces("application/json")
@Consumes("application/json")
public class ConverterController {
	@EJB
	private CurrencyConverterLocal converter;

	public ConverterController() {
		// TODO Auto-generated constructor stub
	}

	@GET
	public Response getCurrency(@QueryParam("euro") float euro) {

		try {
			float dollars = converter.converti(euro);
			return Response.ok().entity(dollars).build();
		} catch (Exception e) {
			return Response.status(500, "errore sul server").build();
		}
	}

}
