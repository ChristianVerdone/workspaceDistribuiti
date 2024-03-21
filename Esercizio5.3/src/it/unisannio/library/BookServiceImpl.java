package it.unisannio.library;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Produces("application/json")
@Consumes("application/json")
@Path("/books")
public class BookServiceImpl {
	private HashMap<String, Book> bookCollection = new HashMap<String, Book>();
	private HashMap<String, List<Order>> orderCollection = new HashMap<String, List<Order>>();

	public BookServiceImpl() {
		bookCollection.put("1234", new Book("1234", "Titolo1", "Autore1"));
		bookCollection.put("4321", new Book("4321", "Titolo2", "Autore2"));
		bookCollection.put("3333", new Book("3333", "Titolo3", "Autore3"));
	}

	@GET
	public Books getBookList() {
		return new Books(new ArrayList<String>(bookCollection.keySet()));
	}

	@GET
	@Path("/{isbn}")
	public Book getBookDetails(@PathParam("isbn") String isbn) {
		return bookCollection.get(isbn);
	}

	@POST
	public Response createBook(Book book) {
		bookCollection.put(book.getIsbn(), book);
		URI uri = null;
		try {
			uri = new URI("/books/" + book.getIsbn());
		} catch (URISyntaxException e) {
			System.err.println(e);
		}
		return Response.created(uri).build();
	}

	@PUT
	@Path("/{isbn}")
	public void setBookDetail(@PathParam("isbn") String isbn, Book book) {
		bookCollection.put(isbn, book);
	}

	@DELETE
	@Path("/{isbn}")
	public void deleteBook(@PathParam("isbn") String isbn) {
		bookCollection.remove(isbn);

	}

	@POST
	@Path("/{isbn}/orders")
	public Response orderBook(@PathParam("isbn") String isbn) {
		List<Order> orderList = orderCollection.get(isbn);
		if (orderList == null) {
			orderList = new ArrayList<Order>();
			orderCollection.put(isbn, orderList);
		}

		int oId = orderCollection.get(isbn).size();
		Order o = new Order(isbn, oId);
		orderList.add(oId, o);
		System.out.println(orderCollection.get(isbn).size());
		System.out.println(oId);
		URI uri = null;
		try {
			uri = new URI("/" + isbn + "/orders/" + oId);
		} catch (URISyntaxException e) {
			System.err.println(e);
		}
		return Response.created(uri).build();
	}

	@GET
	@Path("{isbn}/order/{orderID}")
	public Order getOrder(@PathParam("isbn") String isbn, @PathParam("orderID") int orderId) {
		List<Order> orderList = orderCollection.get(isbn);
		Order o;
		try {
			o = orderList.get(orderId);
			return o;
		} catch (IndexOutOfBoundsException e) {
			System.err.println(e);
			return null;
		}
	}
}
