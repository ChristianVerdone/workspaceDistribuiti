import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;

import it.unisannio.hotelAgency.Hotel;
import it.unisannio.hotelAgency.Order;
import it.unisannio.hotelAgency.Rate;
import it.unisannio.hotelAgency.ResourceList;

public class HotelAgencyClient {
	public static void main(String[] args) {
		WebClient client = WebClient.create("http://localhost:6000/Esercizio5.4/rest/hotelAgency");
		Hotel hotel;
		Order order;
		int creditCard = 123;

		System.out.println("----------search----------\n");
		client.accept("application/xml").type("application/xml");
		client.path("/hotels");
		client.query("checkin", "01-03-2019").query("checkout", "30-06-2019").query("city", "Benevento");
		ResourceList<Integer> list = client.get(ResourceList.class);
		for (Integer i : list.getList()) {
			System.out.println("hotelId: " + i);
		}

		System.out.println("\n----------getHotelDetails----------\n");
		client.reset();
		client.accept("application/xml").type("application/xml");
		client.path("/hotels/" + list.getList().get(0));
		hotel = client.get(Hotel.class);
		System.out.println(hotel);

		System.out.println("\n----------reserve----------\n");
		client.reset();
		client.accept("application/xml").type("application/xml");
		client.path("/hotels/users/" + creditCard + "/bookings");
		Rate rate = hotel.getRates().get(0);
		Response resp = client.post(rate);
		System.out.println(resp.getLocation());

		System.out.println("\n----------getConfirmationDetails----------\n");
		int id = 0;
		client.reset();
		client.accept("application/xml").type("application/xml");
		client.path("/hotels/bookings/" + id);
		order = client.get(Order.class);
		System.out.println(order);

		System.out.println("\n----------listMyBooking----------\n");
		client.reset();
		client.accept("application/xml").type("application/xml");
		client.path("/hotels/users/" + creditCard + "/bookings");
		list = client.get(ResourceList.class);

		for (Integer i : list.getList()) {
			System.out.println("confId: " + i);
			client.reset();
			client.accept("application/xml").type("application/xml");
			client.path("/hotels/bookings/" + i);
			order = client.get(Order.class);
			System.out.println(order);

			/*
			 * ALTERNATIVA client.reset();
			 * client.accept("application/xml").type("application/xml");
			 * client.path("/hotels/" + i); hotel = client.get(Hotel.class);
			 * System.out.println(hotel);
			 */
		}

	}
}
