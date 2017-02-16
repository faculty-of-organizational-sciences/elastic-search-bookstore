package rs.ac.bg.fon.ai.bookstore;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rs.ac.bg.fon.ai.bookstore.indexing.AuthorIndexer;
import rs.ac.bg.fon.ai.bookstore.indexing.BookIndexer;
import rs.ac.bg.fon.ai.bookstore.services.BookstorePersistance;
import rs.ac.bg.fon.ai.bookstore.services.SimpleBookstorePersistance;
import rs.ac.bg.fon.ai.bookstore.services.search.SearchService;

public class Main {
	
	private static Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		BookstorePersistance persistance = new SimpleBookstorePersistance();
		
//		BookIndexer bIndexer = new BookIndexer();
//		bIndexer.indexBooks(persistance.loadAllBooks());
//		
//		AuthorIndexer aIndexer = new AuthorIndexer();
//		aIndexer.indexAuthors(persistance.loadAllAuthors());
		
		SearchService search = new SearchService();
		
		logger.info("Searching for all books with title having a word 'Beauty'");
		List<Long> books1 = search.searchBooks("The", 10, 1);
		
		for (Long id : books1) {
			logger.info(persistance.getBookById(id));
		}
		
		logger.info("Searching for all authors with name starting with 'm'");
		List<Long> authors = search.searchAuthors("m", 10, 1);
		
		for (Long id : authors) {
			logger.info(persistance.getAuthorById(id));
		}
		
		logger.info("Searching for authors having a book title with a word 'History'");
		List<Long> authors1 = search.searchAuthorsWithBookTitle("History", 10, 1);
		
		for (Long id : authors1) {
			logger.info(persistance.getAuthorById(id));
		}
		
	}

}
