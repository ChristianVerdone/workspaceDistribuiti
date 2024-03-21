package it.unisannio.math;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Produces("application/json")
@Path("products")
public interface Multiplier {

	@GET
	Product multiply(@QueryParam("a") float a, @QueryParam("b") float b);

}
