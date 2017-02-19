package rs.ac.bg.fon.ai.bookstore.indexing;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rs.ac.bg.fon.ai.bookstore.domain.Book;

public class BookIndexer {
	
	private Logger logger = LogManager.getLogger();
	
	public void indexBooks(List<Book> books) {
		logger.info("Indexing books");
		
		for (Book book : books) {
			indexBook(book);
		}
	}

	public void indexBook(Book book) {
		logger.info("Indexing book " + book);
		
		// TODO: index book instance
	}
	
	public void deleteIndex() {
		logger.info("Deleting index " + IndexName.BOOK_INDEX.value());

		// TODO: delete book index
		
		logger.info("Finished deleting index " + IndexName.BOOK_INDEX.value());
	}
}
