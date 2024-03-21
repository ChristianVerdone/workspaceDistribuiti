package it.unisannio.hotelAgency;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "hotel")
public class Hotel implements Serializable {

	public Hotel() {
	}

	public Hotel(int id, String name, String city, List<Rate> rates) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.rates = rates;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<Rate> getRates() {
		return rates;
	}

	public void setRates(List<Rate> rates) {
		this.rates = rates;
	}

	@Override
	public String toString() {
		Iterator<Rate> e = rates.iterator();
		StringBuffer s = new StringBuffer();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		while (e.hasNext()) {
			Rate r = (Rate) e.next();
			s.append(sdf.format(r.getStart()) + " " + sdf.format(r.getEnd()) + " " + r.getRoomType() + " "
					+ r.getPrice() + "\n");
		}
		return "[" + id + "] \nname: " + name + "\ncity: " + city + "\n" + s;
	}

	private int id;
	private String name, city;
	private List<Rate> rates;
}
