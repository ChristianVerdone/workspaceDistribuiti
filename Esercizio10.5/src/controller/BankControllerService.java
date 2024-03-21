package controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

public interface BankControllerService {

	@POST
	@Path("/customers/{cf}/accounts")
	public Response createAccount(@PathParam("cf") String cf, @FormParam("amount") double amount) throws Exception;

	@POST
	@Path("/accounts/{accountNumber}/transactions/deposit")
	public Response deposit(@PathParam("accountNumber") int accountNumber, @FormParam("amount") double amount)
			throws Exception;

	@POST
	@Path("/accounts/{accountNumber}/transactions/withdraw")
	public Response withdraw(@PathParam("accountNumber") int accountNumber, @FormParam("amount") double amount)
			throws Exception;

	@POST
	@Path("/customers")
	public Response createCustomer(@FormParam("cf") String cf, @FormParam("firstName") String fn,
			@FormParam("lastName") String ln) throws Exception;

	// @POST
//	@Path("/customers")
//	@Consumes("application/x-www-form-urlencoded")
//	public Response createCustomer(MultivaluedMap<String, String> formParams);

	@GET
	@Path("/customers/{cf}/accounts")
	public Response allCustomerAccounts(@PathParam("cf") String cf);

	@POST
	@Path("accounts/{accountNumber}/transactions/transfer") // TODO
	public Response transfer(@PathParam("accountNumber") int accountNumber,
			@FormParam("otherAccountNumber") int otherAccountNumber, @FormParam("amount") double amount);

}
