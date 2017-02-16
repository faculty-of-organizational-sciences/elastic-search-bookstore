package rs.ac.bg.fon.ai.bookstore.services;

import java.util.ArrayList;
import java.util.List;

import rs.ac.bg.fon.ai.bookstore.domain.Author;
import rs.ac.bg.fon.ai.bookstore.domain.Book;

public class SimpleBookstorePersistance implements BookstorePersistance {
	
	private static List<Book> books;
	private static List<Author> authors;
	
	static {
		books = new ArrayList<Book>();
		authors = new ArrayList<Author>();
		
		// Creating authors
		Author author1 = new Author(1, "Umberto", "Eco");	
		Author author2 = new Author(2, "Colleen", "McCullough");	
		Author author3 = new Author(3, "Jean", "Auel");	
		Author author4 = new Author(4, "Margaret", "Mitchell");
		
		authors.add(author1);
		authors.add(author2);
		authors.add(author3);
		authors.add(author4);

		// Declaring books
		Book book1 = new Book(1, "The Name of the Rose", 1980, "9780156001311");
		Book book2 = new Book(2, "History of Beauty", 2004, "9780847826469");
		Book book3 = new Book(3, "The Thorn Birds", 1977, "9780380018178");
		Book book4 = new Book(4, "The Clan of the Cave Bear", 1980, "9780553381672");
		Book book5 = new Book(5, "Gone with the Wind", 1936, "9780446675536");
		
		books.add(book1);
		books.add(book2);
		books.add(book3);
		books.add(book4);
		books.add(book5);

		// setting relationships
		book1.setAuthor(author1);
		book2.setAuthor(author1);
		
		author1.addBook(book1);
		author1.addBook(book2);
		
		book3.setAuthor(author2);
		author2.addBook(book3);

		book4.setAuthor(author3);
		author3.addBook(book4);

		book5.setAuthor(author4);
		author4.addBook(book5);
	}

	/* (non-Javadoc)
	 * @see rs.ac.bg.fon.ai.bookstore.services.BookstorePersistance#loadAllBooks()
	 */
	public List<Book> loadAllBooks() {
		return books;
	}
	
	/* (non-Javadoc)
	 * @see rs.ac.bg.fon.ai.bookstore.services.BookstorePersistance#getBookById(long)
	 */
	public Book getBookById(long id) {
		for (Book book : books) {
			if (book.getId() == id) {
				return book;
			}
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see rs.ac.bg.fon.ai.bookstore.services.BookstorePersistance#loadAllAuthors()
	 */
	public List<Author> loadAllAuthors() {
		return authors;
	}
	
	/* (non-Javadoc)
	 * @see rs.ac.bg.fon.ai.bookstore.services.BookstorePersistance#getAuthorById(long)
	 */
	public Author getAuthorById(long id) {
		for (Author author : authors) {
			if (author.getId() == id) {
				return author;
			}
		}
		return null;
	}
}
