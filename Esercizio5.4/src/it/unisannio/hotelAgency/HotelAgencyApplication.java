package it.unisannio.hotelAgency;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/rest/hotelAgency")

public class HotelAgencyApplication extends Application {

		public Set<Class<?>> getClasses() {
			Set<Class<?>> s = new HashSet<Class<?>>();
			s.add(it.unisannio.hotelAgency.HotelAgencyServiceImpl.class);
			return s;
		}

		public Set<Object> getSingletons() {
			Set<Object> s = new HashSet<Object>();
			s.add(new it.unisannio.hotelAgency.HotelAgencyServiceImpl());
			return s;
		}
}
