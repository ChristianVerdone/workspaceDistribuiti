package it.unisannio.rest;

import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Consumes("text/plain")
@Produces("text/plain")
@Path("/strings")

public interface StringResource {

	@POST
	public Response setString(String s);
	
	@GET
	@Path("/{id}")
	public Response getString(@PathParam("id") int id);
	
	/*
	 @GET
	 @Param("/{id}")
	 */
	
}
