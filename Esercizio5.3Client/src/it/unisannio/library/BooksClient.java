package it.unisannio.library;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;

public class BooksClient {
	public static void main(String[] args) {

		WebClient client = WebClient.create("http://localhost:6000/Esercizio5.3/rest/library");
		Books books;
		Book b;

		System.out.println("--------getBookList------------\n");
		client.accept("application/json").type("application/json");
		client.path("/books");
		books = client.get(Books.class);
		System.out.println("Intera collezione di libri: " + books);
		System.out.println();

		System.out.println("---------getBookDetails-----------\n");
		client.reset();
		client.accept("application/json").type("application/json");
		client.path("/books/1234");
		b = client.get(Book.class);
		System.out.println(b);
		System.out.println();

		System.out.println("--------------createBook--------------\n");
		client.reset();
		client.accept("application/json").type("application/json");
		client.path("/books");
		b = new Book("9866", "titoloLibro", "unAutore");
		Response res = client.post(b);
		System.out.println("Book: " + b + "\n" + res.getLocation());
		System.out.println();

		System.out.println("--------------setBookDetail---------------\n");
		client.reset();
		client.accept("application/json").type("application/json");
		client.path("/books/9866");
		b = client.get(Book.class);
		System.out.println("dettaglio libro prima \n" + b);
		System.out.println();

		b.setTitle("titoloModificato");
		client.reset();
		client.accept("application/json").type("application/json");
		client.path("/books/1234");
		client.put(b);
		b = client.get(Book.class);
		System.out.println("dettaglio libro dopo \n" + b);
		System.out.println();

		System.out.println("--------------deleteBook---------------\n");
		client.reset();
		client.accept("application/json").type("application/json");
		client.path("/books");
		books = client.get(Books.class);
		System.out.println("Intera collezione di libri: " + books);
		System.out.println();

		client.reset();
		client.accept("application/json").type("application/json");
		client.path("/books/9866");
		client.delete();
		System.out.println("delete: /books/9866 \n");

		client.reset();
		client.accept("application/json").type("application/json");
		client.path("/books");
		books = client.get(Books.class);
		System.out.println("Intera collezione di libri: " + books);
		System.out.println();

		System.out.println("--------------orderBook---------------\n");
		client.reset();
		client.accept("application/json").type("application/json");
		client.path("/books/1234/orders");
		Response resp = client.post(null);
		System.out.println(resp.getLocation() + "\n");

		System.out.println("--------------getOrder---------------\n");
		client.reset();
		client.accept("application/json").type("application/json");
		client.path("/books/1234/order/0");
		Order or = client.get(Order.class);
		System.out.println(or);
	}
}
