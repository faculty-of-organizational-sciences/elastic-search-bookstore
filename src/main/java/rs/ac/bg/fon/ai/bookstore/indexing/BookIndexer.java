package rs.ac.bg.fon.ai.bookstore.indexing;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import rs.ac.bg.fon.ai.bookstore.domain.Author;
import rs.ac.bg.fon.ai.bookstore.domain.Book;
import rs.ac.bg.fon.ai.bookstore.indexing.administration.ESIndex;
import rs.ac.bg.fon.ai.bookstore.indexing.administration.ElasticClient;

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
		
		try {
			XContentBuilder builder = XContentFactory.jsonBuilder().startObject();
			
			builder.field("title", book.getTitle() != null ? book.getTitle() : "");
			builder.field("isbn", book.getIsbn() != null ? book.getIsbn() : "");
			builder.field("year", book.getYear());
			builder.field("score", book.getScore());
			
			// index author books
			List<Author> authors = book.getAuthors();
			
			builder.startArray("authors");
			
			for (Author author : authors) {
				builder.startObject();
				builder.field("id", author.getId());
				builder.endObject();
			}
			builder.endArray();
			
            builder.endObject();
			
			@SuppressWarnings("unused")
			IndexResponse response = ElasticClient.getInstance().getClient()
				.prepareIndex(
					ESIndex.BOOK.name().toLowerCase(), 
					ESIndex.BOOK.getIndexName(), 
					String.valueOf(book.getId()))
		        .setSource(builder)
		        .get();
		} catch (IOException e) {
			logger.error("Error", e);
		}
	}
}
