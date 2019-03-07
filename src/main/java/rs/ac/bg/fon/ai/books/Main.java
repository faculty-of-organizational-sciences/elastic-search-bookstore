package rs.ac.bg.fon.ai.books;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rs.ac.bg.fon.ai.books.domain.Author;
import rs.ac.bg.fon.ai.books.domain.Book;
import rs.ac.bg.fon.ai.books.indexing.AuthorIndexer;
import rs.ac.bg.fon.ai.books.indexing.BookIndexer;
import rs.ac.bg.fon.ai.books.indexing.BookstoreIndexAdministration;
import rs.ac.bg.fon.ai.books.persistance.BookstorePersistance;
import rs.ac.bg.fon.ai.books.persistance.ListBasedBookstorePersistance;
import rs.ac.bg.fon.ai.books.services.BookstoreSearchService;
import rs.ac.bg.fon.ai.books.services.BookstoreSearchServiceImpl;

public class Main {
	
	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
//		BookstoreIndexAdministration esAdmin = new BookstoreIndexAdministration();
//		esAdmin.deleteAllIndices();
//		esAdmin.createAllIndices();
		
		BookstorePersistance persistance = new ListBasedBookstorePersistance();
		
//		BookIndexer bIndexer = new BookIndexer();
//		bIndexer.indexBooks(persistance.loadAllBooks());
//		
//		AuthorIndexer aIndexer = new AuthorIndexer();
//		aIndexer.indexAuthors(persistance.loadAllAuthors());
		
		BookstoreSearchService searchService = new BookstoreSearchServiceImpl();
		
		logger.info("Query 1: Print all books");

		List<Long> result1 = searchService.retrieveAllBooks();
		
		for (Long id : result1) {
			Optional<Book> optionalBook = persistance.getBookById(id);
			
			if (optionalBook.isPresent())
				logger.info(optionalBook.get());
		}
		
		// We will skip "Query 2" since it is a URL based search
		
		logger.info("Query 3: Print books from year 1980");

		List<Long> result3 = searchService.retrieveBooksFromYear(1980);
		
		for (Long id : result3) {
			Optional<Book> optionalBook = persistance.getBookById(id);
			
			if (optionalBook.isPresent())
				logger.info(optionalBook.get());
		}
		
		
		logger.info("Query 4: Print books from year 1980 and before");

		List<Long> result4 = searchService.retrieveBooksFromYearAndBefore(1980);
		
		for (Long id : result4) {
			Optional<Book> optionalBook = persistance.getBookById(id);
			
			if (optionalBook.isPresent())
				logger.info(optionalBook.get());
		}
		
		logger.info("Query 5: Print books from year 1980 and before, sorted desc by score");

		List<Long> result5 = searchService.retrieveBooksFromYearAndBeforeSorted(1980);
		
		for (Long id : result5) {
			Optional<Book> optionalBook = persistance.getBookById(id);
			
			if (optionalBook.isPresent())
				logger.info(optionalBook.get());
		}
		
		
		logger.info("Query 6: Print books from year and before 1980 with pagination");

		List<Long> result6 = searchService.retrieveBooksFromYearAndBeforeWithPagination(1980, 1, 1);
		
		for (Long id : result6) {
			Optional<Book> optionalBook = persistance.getBookById(id);
			
			if (optionalBook.isPresent())
				logger.info(optionalBook.get());
		}
		
		// We will skip "Query 7" since it doesn't return any results
		
		logger.info("Query 8: Print all male author information");

		List<Long> result8 = searchService.retrieveMaleAuthors();
		
		for (Long id : result8) {
			Optional<Author> optionalAuthor = persistance.getAuthorById(id);
			
			if (optionalAuthor.isPresent())
				logger.info(optionalAuthor.get());
		}
		
		
		logger.info("Query 9: Print books having in their title any of the words from the phrase \"color of magic\"");

		List<Long> result9 = searchService.searchBooksHavingAnyWordInTitle("color of magic");
		
		for (Long id : result9) {
			Optional<Book> optionalBook = persistance.getBookById(id);
			
			if (optionalBook.isPresent())
				logger.info(optionalBook.get());
		}
		
		
		logger.info("Query 10: Print books having all words from \"color of magic\" in their title");

		List<Long> result10 = searchService.searchBooksHavingAllWordsInTitle("color of magic");
		
		for (Long id : result10) {
			Optional<Book> optionalBook = persistance.getBookById(id);
			
			if (optionalBook.isPresent())
				logger.info(optionalBook.get());
		}
		
		
		logger.info("Query 11: Print books having \"good o\" in their title.");

		List<Long> result11 = searchService.searchBooks("good o");
		
		for (Long id : result11) {
			Optional<Book> optionalBook = persistance.getBookById(id);
			
			if (optionalBook.isPresent())
				logger.info(optionalBook.get());
		}
		
		
		logger.info("Query 12: Print books having fuzzy titele \"magig\"");
		
		List<Long> result12 = searchService.fuzzySearchBooks("magig");
		
		for (Long id : result12) {
			Optional<Book> optionalBook = persistance.getBookById(id);
			
			if (optionalBook.isPresent())
				logger.info(optionalBook.get());
		}
		
		
		logger.info("Query 13: Print books of the author with id 3");

		List<Long> result13 = searchService.searchForBooksOfAuthor(3, "magic");
		
		for (Long id : result13) {
			Optional<Book> optionalBook = persistance.getBookById(id);
			
			if (optionalBook.isPresent())
				logger.info(optionalBook.get());
		}
	}
}
