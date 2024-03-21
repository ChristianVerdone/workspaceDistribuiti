package it.unisannio.library;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "books")
public class Books {
	private List<String> collection;

	public Books() {
	}

	public Books(List<String> bs) {
		collection = bs;
	}

	public List<String> getCollection() {
		return collection;
	}

	public void setCollection(List<String> collection) {
		this.collection = collection;
	}

	@Override
	public String toString() {
		return "Books [collection=" + collection + "]";
	}

	
}
