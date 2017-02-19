package rs.ac.bg.fon.ai.bookstore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rs.ac.bg.fon.ai.bookstore.services.BookstorePersistance;
import rs.ac.bg.fon.ai.bookstore.services.SimpleBookstorePersistance;

public class Main {
	
	private static Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		// instantiate persistence
		BookstorePersistance persistance = new SimpleBookstorePersistance();
		
		// create book indexer and index all books

		
		// create author indexer and index all authors

		
		// instantiate search service
		
		logger.info("Searching for all books with title having a word 'Beauty'");

		
		logger.info("Searching for all authors with name starting with 'm'");

		
		logger.info("Searching for authors having a book title with a word 'History'");
		
	}

}
