package it.unisannio.library;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "book")
public class Book {
	private String isbn;
	private String title;
	private String author;

	public Book() {
	}

	public Book(String i, String t, String a) {
		this.isbn = i;
		this.title = t;
		this.author = a;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book: isbn= " + isbn + " title = " + title + " Author = " + author;
	}

}
