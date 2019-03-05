package rs.ac.bg.fon.ai.bookstore;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rs.ac.bg.fon.ai.bookstore.domain.Author;
import rs.ac.bg.fon.ai.bookstore.domain.Book;
import rs.ac.bg.fon.ai.bookstore.indexing.AuthorIndexer;
import rs.ac.bg.fon.ai.bookstore.indexing.BookIndexer;
import rs.ac.bg.fon.ai.bookstore.indexing.administration.ElasticsearchAdministration;
import rs.ac.bg.fon.ai.bookstore.services.SimpleBookstorePersistance;
import rs.ac.bg.fon.ai.bookstore.services.search.BookstoreSearchService;

public class Main {
	
	private static final Logger logger = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		ElasticsearchAdministration esAdmin = new ElasticsearchAdministration();
		esAdmin.deleteAllIndexes();
		esAdmin.createAllIndexes();
		
		SimpleBookstorePersistance persistance = new SimpleBookstorePersistance();
		
		BookIndexer bIndexer = new BookIndexer();
		bIndexer.indexBooks(persistance.loadAllBooks());
		
		AuthorIndexer aIndexer = new AuthorIndexer();
		aIndexer.indexAuthors(persistance.loadAllAuthors());
		
		BookstoreSearchService searchService = new BookstoreSearchService();
		
		/*
		 * Example 1: Retrieve books from 1980
		 */
		List<Long> result1 = searchService.retrieveBooksFromYear(1980);
		
		logger.info("Print books from year 1980");
		for (Long id : result1) {
			Optional<Book> optionalBook = persistance.getBookById(id);
			
			if (optionalBook.isPresent())
				logger.info(optionalBook.get());
		}
		
		
		/*
		 * Example 2: Retrieve books from and before 1980
		 */
		List<Long> result2 = searchService.retrieveBooksFromYearAndBefore(1980);
		
		logger.info("Print books from year 1980 and before");
		for (Long id : result2) {
			Optional<Book> optionalBook = persistance.getBookById(id);
			
			if (optionalBook.isPresent())
				logger.info(optionalBook.get());
		}
		
		
		/*
		 * Example 3: Sorting
		 */
		List<Long> result3 = searchService.retrieveBooksFromYearAndBeforeSorted(1980);
		
		logger.info("Print books from year 1980 and before, sorted desc by score");
		for (Long id : result3) {
			Optional<Book> optionalBook = persistance.getBookById(id);
			
			if (optionalBook.isPresent())
				logger.info(optionalBook.get());
		}
		
		
		/*
		 * Example 4: Pagination
		 */
		List<Long> result4 = searchService.retrieveBooksFromYearAndBeforeWithPagination(1980, 1, 1);
		
		logger.info("Print books from year and before 1980 with pagination");
		for (Long id : result4) {
			Optional<Book> optionalBook = persistance.getBookById(id);
			
			if (optionalBook.isPresent())
				logger.info(optionalBook.get());
		}
		
		
		/*
		 * Example 6: Retrieve male authors - match
		 */
		List<Long> result5 = searchService.retrieveMaleAuthors();
		
		logger.info("Print male author information");
		for (Long id : result5) {
			Optional<Author> optionalAuthor = persistance.getAuthorById(id);
			
			if (optionalAuthor.isPresent())
				logger.info(optionalAuthor.get());
		}
		
		
		/*
		 * Example 7: Search books having any word in title
		 */
		List<Long> result6 = searchService.searchBooksHavingAnyWordInTitle("color of magic");
		
		logger.info("Print books having in their title any of the words from the phrase \"color of magic\"");
		for (Long id : result6) {
			Optional<Book> optionalBook = persistance.getBookById(id);
			
			if (optionalBook.isPresent())
				logger.info(optionalBook.get());
		}
		
		
		/*
		 * Example 8: Search books having any word in title
		 */
		List<Long> result7 = searchService.searchBooksHavingAllWordsInTitle("color of magic");
		
		logger.info("Print books having all words from \"color of magic\" in their title");
		for (Long id : result7) {
			Optional<Book> optionalBook = persistance.getBookById(id);
			
			if (optionalBook.isPresent())
				logger.info(optionalBook.get());
		}
		
		
		/*
		 * Example 9: Search books having search query in title. This simulates a search where user 
		 * starts typing a name of the book.
		 */
		List<Long> result8 = searchService.searchBooks("good o");
		
		logger.info("Print books having \"good o\" in their title.");
		for (Long id : result8) {
			Optional<Book> optionalBook = persistance.getBookById(id);
			
			if (optionalBook.isPresent())
				logger.info(optionalBook.get());
		}
		
		
		/*
		 * Example 10: Fuzzy searching for books having query word in title
		 */
		List<Long> result9 = searchService.fuzzySearchBooks("magig");
		
		logger.info("Print books having fuzzy titele \"magig\"");
		for (Long id : result9) {
			Optional<Book> optionalBook = persistance.getBookById(id);
			
			if (optionalBook.isPresent())
				logger.info(optionalBook.get());
		}
		
		
		/*
		 * Example 11: Search books of given author
		 */
		List<Long> result10 = searchService.searchForBooksOfAuthor(3, "magic");
		
		logger.info("Print books of the author with id 3");
		for (Long id : result10) {
			Optional<Book> optionalBook = persistance.getBookById(id);
			
			if (optionalBook.isPresent())
				logger.info(optionalBook.get());
		}
	}
}
