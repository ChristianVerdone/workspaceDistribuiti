package it.unisannio.hotelAgency;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

public class HotelAgencyServiceImpl implements HotelAgencyService {
	private List<Hotel> hotels = new ArrayList<Hotel>();
	private ArrayList<Order> orders = new ArrayList<Order>();
	private int confId = -1;
	private Map<Integer, List<Order>> reservations = new HashMap<Integer, List<Order>>();

	public HotelAgencyServiceImpl() {
		List<Rate> HotelRates;
		Date firstDate, lastDate;
		int id = 0;
		try {
			HotelRates = new ArrayList<Rate>();
			firstDate = (Date) (new SimpleDateFormat("dd-MM-yyyy")).parse("01-03-2019");
			lastDate = (Date) (new SimpleDateFormat("dd-MM-yyyy")).parse("30-06-2019");
			HotelRates.add(new Rate(id, "Single", firstDate, lastDate, 100.0));
			HotelRates.add(new Rate(id, "Double", firstDate, lastDate, 200.0));

			hotels.add(new Hotel(id, "Hotel Roma", "Benevento", HotelRates));
			// orders.put(0, new ArrayList<Order>());

			HotelRates = new ArrayList<Rate>();
			id++;
			firstDate = (Date) (new SimpleDateFormat("dd-MM-yyyy")).parse("01-07-2019");
			lastDate = (Date) (new SimpleDateFormat("dd-MM-yyyy")).parse("30-08-2019");
			HotelRates.add(new Rate(id, "Single", firstDate, lastDate, 130.0));
			HotelRates.add(new Rate(id, "Double", firstDate, lastDate, 240.0));

			hotels.add(new Hotel(id, "Hotel Napoli", "Benevento", HotelRates));
			// orders.put(id, new ArrayList<Order>());

		} catch (ParseException e) {
			System.err.println(e);
		}
	}

	@Override
	public ResourceList<Integer> search(String checkin, String checkout, String city) {
		List<Integer> matchingRates = new ArrayList<Integer>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			Date checkinD = sdf.parse(checkin);
			Date checkoutD = sdf.parse(checkout);
			for (int i = 0; i < hotels.size(); i++) {
				Hotel hotel = hotels.get(i);
				List<Rate> rates = hotel.getRates();

				for (int j = 0; j < rates.size(); j++) {
					Rate aRate = rates.get(j);

					if ((checkinD.equals(aRate.getStart()) || checkinD.after(aRate.getStart()))
							&& (checkoutD.equals(aRate.getEnd()) || checkoutD.before(aRate.getEnd()))
							&& hotel.getCity().equals(city)) {
						matchingRates.add(hotel.getId());
						break;
					}
				}
			}
			return new ResourceList<Integer>(matchingRates);
		} catch (ParseException e) {
			System.err.println(e);
			return null;
		}
	}

	@Override
	public Hotel getHotelDetails(int hotelId) {
		return hotels.get(hotelId);
	}

	@Override
	public Response reserve(Rate rate, int creditCard) {

		Order order = new Order(rate, new Date());
		List<Order> listOrder = reservations.get(creditCard);
		if (listOrder == null) {
			listOrder = new ArrayList<Order>();
			reservations.put(creditCard, listOrder);
		}
		listOrder.add(order);

		confId++;
		orders.add(order);
		URI uri = null;
		try {
			uri = new URI("/users/" + creditCard + "/bookings/" + confId);
		} catch (URISyntaxException e) {
			System.err.println("URI error");
		}
		return Response.created(uri).build();
	}

	@Override
	public Order getConfirmationDetails(int confId) {
		return orders.get(confId);
	}

	@Override
	public ResourceList<Integer> listMyBooking(int credtiCard) {
		List<Order> list = reservations.get((Integer) credtiCard);
		ArrayList<Integer> orders = new ArrayList<Integer>();
		for (Order o : list) {
			orders.add(this.orders.indexOf(o));
		}
		return new ResourceList<Integer>(orders);
	}
//ALTERNATIVA
//	@Override
//	public ResourceList<Integer> listMyBooking(int credtiCard) {
//		List<Order> list = reservations.get((Integer) credtiCard);
//		ArrayList<Integer> hotelId = new ArrayList<Integer>();
//		for (Order o : list) {
//			hotelId.add(o.getRate().getHotelId());
//		}
//		return new ResourceList<Integer>(hotelId);
//	}

}
