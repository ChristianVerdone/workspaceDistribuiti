package it.unisannio.hotelAgency;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

@WebService(targetNamespace = "http://hotelAgency.unisannio.it/", endpointInterface = "it.unisannio.hotelAgency.HotelAgencyService", portName = "HotelAgencyServiceImplPort", serviceName = "HotelAgencyServiceImplService")
public class HotelAgencyServiceImpl implements HotelAgencyService {
	private List<Hotel> hotels = new ArrayList<Hotel>();
	private ArrayList<Order> orders = new ArrayList<Order>();

	private int confId = -1;

// la mappa relazione l'ordine con chi l'ha effettuato tramite credit card
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
	public List<Integer> search(Date checkin, Date checkout, String city) {

		List<Integer> matchingRates = new ArrayList<Integer>();

		for (int i = 0; i < hotels.size(); i++) {
			Hotel hotel = hotels.get(i);
			List<Rate> rates = hotel.getRates();

			for (int j = 0; j < rates.size(); j++) {
				Rate aRate = rates.get(j);

				if ((checkin.equals(aRate.getStart()) || checkin.after(aRate.getStart()))
						&& (checkout.equals(aRate.getEnd()) || checkout.before(aRate.getEnd()))
						&& hotel.getCity().equals(city)) {
					matchingRates.add(hotel.getId());
					break;
				}
			}
		}
		return matchingRates;
	}

	@Override
	public Hotel getHotelDetails(int hotelId) {
		return hotels.get(hotelId);
	}

	@Override
	public int reserve(Rate rate, int creditCard) {

		Order order = new Order(rate, new Date());
		List<Order> listOrder = reservations.get(creditCard);
		if (listOrder == null) {
			listOrder = new ArrayList<Order>();
			reservations.put(creditCard, listOrder);
		}
		listOrder.add(order);

		confId++;
		orders.add(order);
		return confId;
	}

	@Override
	public Order getConfirmationDetails(int confId) {
		return orders.get(confId);
	}

	@Override
	public List<Integer> listMyBooking(int creditCard) {
		List<Order> list = reservations.get((Integer) creditCard);
		ArrayList<Integer> hotelId = new ArrayList<Integer>();
		for (Order o : list) {
			hotelId.add(o.getRate().getHotelId());
		}
		return hotelId;
	}

}