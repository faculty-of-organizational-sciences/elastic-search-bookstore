package rs.ac.bg.fon.ai.books.indexing;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import rs.ac.bg.fon.ai.books.domain.Author;
import rs.ac.bg.fon.ai.books.domain.Book;
import rs.ac.bg.fon.ai.elasticsearch.ElasticClient;

public class BookIndexer {
	
	private static final Logger logger = LogManager.getLogger(BookIndexer.class);
	
	public void indexBooks(List<Book> books) {
		logger.debug("Indexing books");
		
		for (Book book : books) {
			indexBook(book);
		}
	}

	public void indexBook(Book book) {
		logger.debug("Indexing a book " + book);
		
		
	}
}
