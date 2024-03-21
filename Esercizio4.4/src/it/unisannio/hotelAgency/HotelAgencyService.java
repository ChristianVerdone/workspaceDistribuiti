package it.unisannio.hotelAgency;

import java.util.Date;
import java.util.List;

import javax.jws.WebService;

@WebService(name = "HotelAgencyService", targetNamespace = "http://hotelAgency.unisannio.it/")
public interface HotelAgencyService {
	public List<Integer> search(Date checkin, Date checkout, String city);

	public Hotel getHotelDetails(int hotelId);

	public int reserve(Rate rate, int creditCard);

	public Order getConfirmationDetails(int confId);

	public List<Integer> listMyBooking(int creditCard);
}
