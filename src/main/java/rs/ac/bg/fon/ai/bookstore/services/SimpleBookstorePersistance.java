package rs.ac.bg.fon.ai.bookstore.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import rs.ac.bg.fon.ai.bookstore.domain.Author;
import rs.ac.bg.fon.ai.bookstore.domain.Book;

public class SimpleBookstorePersistance {
	
	private static List<Book> books;
	private static List<Author> authors;
	
	static {
		books = new ArrayList<Book>();
		authors = new ArrayList<Author>();
		
		// Creating authors
		Author author1 = new Author(1, "Larry", "Niven", "male");	
		Author author2 = new Author(2, "Jerry", "Pournelle", "male");	
		Author author3 = new Author(3, "Terry", "Pratchett", "male");	
		Author author4 = new Author(4, "Alice", "Hoffman", "female");
		
		authors.add(author1);
		authors.add(author2);
		authors.add(author3);
		authors.add(author4);

		// Declaring books
		Book book1 = new Book(1, "The Mote in God's Eye", 1974, "0-671-21833-6", 4.1);
		Book book2 = new Book(2, "The Color of Magic", 1983, "0-86140-324-X", 4);
		Book book3 = new Book(3, "Good Omens: The Nice and Accurate Prophecies of Agnes Nutter, Witch", 1980, "0-575-04800-X", 4.2);
		Book book4 = new Book(4, "The Rules of Magic", 2017, "978-1501137488", 4);
		
		books.add(book1);
		books.add(book2);
		books.add(book3);
		books.add(book4);

		// setting relationships
		book1.addAuthor(author1);
		book1.addAuthor(author2);
		book2.addAuthor(author3);
		book3.addAuthor(author3);
		book4.addAuthor(author4);

		author1.addBook(book1);
		author2.addBook(book1);
		author3.addBook(book2);
		author3.addBook(book3);
		author4.addBook(book4);
	}

	public List<Book> loadAllBooks() {
		return books;
	}
	
	public Optional<Book> getBookById(long id) {
		return books.stream().filter(b -> b.getId() == id).findFirst();
	}
	
	public List<Author> loadAllAuthors() {
		return authors;
	}
	
	public Optional<Author> getAuthorById(long id) {
		return authors.stream().filter(a -> a.getId() == id).findFirst();
	}
}
