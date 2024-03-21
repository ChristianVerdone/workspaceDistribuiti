package it.unisannio.hotelAgency;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Produces("application/xml")
@Consumes("application/xml")
@Path("/hotels")
public interface HotelAgencyService {
   
   @GET 	// 	/hotels?checkin=...& checkout=...&city=...	
   public ResourceList<Integer> search(@QueryParam("checkin") String checkin, @QueryParam("checkout") String checkout, @QueryParam("city") String city);
   
   @GET 	//	/hotels/<id>
   @Path("/{id}")
   public Hotel getHotelDetails(@PathParam("id") int hotelId); 
   
  
   @POST	//	/hotels/users/{cc}/bookings
   @Path("/users/{cc}/bookings")
   public Response reserve(Rate rate, @PathParam("cc") int creditCard);
/*   
   @POST	//	/hotels/bookings?creditCard=...
   @Path("bookings")
   public Response reserve(Rate rate, @QueryParam("creditCard") int creditCard);
*/   
   @GET		// /hotels/bookings/{id}
   @Path("/bookings/{id}")
   public Order getConfirmationDetails(@PathParam("id") int confId);  
   
   @GET		// /hotels/users/{cc}/bookings
   @Path("/users/{cc}/bookings")
   public ResourceList<Integer> listMyBooking(@PathParam("cc") int credtiCard);
/*   
   @GET 	// hotels/bookings?creditcard= ...
   @Path("/bookings")
   public ResourceList<Integer> listMyBooking(@QueryParam("creditcard") int creditCard);
*/   
   
}