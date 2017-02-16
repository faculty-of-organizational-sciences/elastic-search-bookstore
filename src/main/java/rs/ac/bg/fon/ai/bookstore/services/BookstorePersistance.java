package rs.ac.bg.fon.ai.bookstore.services;

import java.util.List;

import rs.ac.bg.fon.ai.bookstore.domain.Author;
import rs.ac.bg.fon.ai.bookstore.domain.Book;

public interface BookstorePersistance {

	List<Book> loadAllBooks();

	Book getBookById(long id);

	List<Author> loadAllAuthors();

	Author getAuthorById(long id);

}