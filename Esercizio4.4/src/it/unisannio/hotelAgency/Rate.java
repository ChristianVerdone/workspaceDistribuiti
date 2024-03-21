package it.unisannio.hotelAgency;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Rate implements Serializable {
	
	public Rate() {}
	
	public Rate(int hotelId, String roomType, Date start, Date end, double price) {
		this.hotelId = hotelId;
		this.roomType = roomType;
		this.start = start;
		this.end = end;
		this.price = price;
	}
	
	public int getHotelId() {
		return hotelId;
	}
	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return "Rate [hotelId=" + hotelId + ", roomType=" + roomType + ", start=" + sdf.format(start) + ", end=" + sdf.format(end) + ", price="
				+ price + "]";
	}



	private int hotelId;
	private String roomType;
	private Date start, end;
	private double price;
	
}
