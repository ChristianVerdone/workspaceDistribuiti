package it.unisannio.scalableRESTSearcher;

import java.rmi.Remote;
import java.rmi.RemoteException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Produces("application/json")
@Path("/resources")
public interface Searcher extends Remote {
	@GET
	public String find(@QueryParam("key") String o) throws RemoteException;

}
