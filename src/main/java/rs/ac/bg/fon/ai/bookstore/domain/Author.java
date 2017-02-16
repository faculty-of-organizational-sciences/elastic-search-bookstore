package rs.ac.bg.fon.ai.bookstore.domain;

import java.util.ArrayList;
import java.util.List;

public class Author {

	private long id;
	private String name;
	private String lastname;
	private List<Book> books;

	public Author() {
		books = new ArrayList<Book>();
	}

	public Author(long id, String name, String lastname) {
		books = new ArrayList<Book>();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	public void addBook(Book book) {
		this.books.add(book);
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", lastname=" + lastname + "]";
	}
	
}
