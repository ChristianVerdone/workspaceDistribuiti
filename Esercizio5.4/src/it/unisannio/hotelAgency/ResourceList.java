package it.unisannio.hotelAgency;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "resourcelist")
public class ResourceList<T> {

	private List<T> list = new ArrayList<T>();

	public ResourceList() {
	}

	public ResourceList(List<T> list) {
		this.list = list;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "ResouceList [list=" + list + "]";
	}

}
