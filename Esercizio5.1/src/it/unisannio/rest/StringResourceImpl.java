package it.unisannio.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;


public class StringResourceImpl implements StringResource{
	
	private List<String> strings = new ArrayList<String>();
	public StringResourceImpl () {
		strings.add("first");
	}
	@Override
	public Response setString(String s) {
		strings.add(s);
		URI uri=null;
		try {
			uri=new URI("/strings/"+(strings.size()-1));
		} catch(URISyntaxException e ) {
			System.err.println("URI error");
		}
		return Response.created(uri).build();
	}

	@Override
	public Response getString(int id) {
		return Response.ok(strings.get(id)).build();
	}
	
	/*
	 public String getString(int id) {
		return strings.get(id);
	}
	 */
}
