package rs.ac.bg.fon.ai.books.persistance;

import java.util.List;
import java.util.Optional;

import rs.ac.bg.fon.ai.books.domain.Author;
import rs.ac.bg.fon.ai.books.domain.Book;

public interface BookstorePersistance {

	List<Book> loadAllBooks();

	Optional<Book> getBookById(long id);

	List<Author> loadAllAuthors();

	Optional<Author> getAuthorById(long id);

}