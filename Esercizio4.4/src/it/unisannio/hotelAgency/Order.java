package it.unisannio.hotelAgency;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	private Rate rate;
	private Date confirmationDate;

	public Order() {
	}

	public Order(Rate rate, Date confirmationDate) {

		this.rate = rate;
		this.confirmationDate = confirmationDate;
	}

	public Rate getRate() {
		return rate;
	}

	public void setRate(Rate rate) {
		this.rate = rate;
	}

	public Date getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(Date confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return "Order [rate=" + rate + ", confirmationDate=" + sdf.format(confirmationDate) + "]";
	}
}
