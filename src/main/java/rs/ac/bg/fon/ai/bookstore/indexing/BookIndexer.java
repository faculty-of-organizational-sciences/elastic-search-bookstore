package rs.ac.bg.fon.ai.bookstore.indexing;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

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
		
		try {
			XContentBuilder builder = XContentFactory.jsonBuilder().startObject();
			
			builder.field("title", book.getTitle() != null ? book.getTitle() : "" );
			builder.field("year", book.getYear());
			
            builder.endObject();
			
			/*
			 * Performing e.g.
			 * PUT http://localhost:9200/book/book/6
			 * 
			 *	{
			 *		"title": "The Name of the Rose",
			 *		"date": 1980
			 *	}
			 */
			
            @SuppressWarnings("unused")
			IndexResponse response = ElasticClient.getInstance().getClient().prepareIndex(IndexName.BOOK_INDEX.value(), IndexType.BOOK.value(), String.valueOf(book.getId()))
		        .setSource(builder)
		        .get();
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	public void deleteIndex() {
		logger.info("Deleting index " + IndexName.BOOK_INDEX.value());
		ElasticClient.getInstance().getClient().admin().indices().delete(new DeleteIndexRequest(IndexName.BOOK_INDEX.value())).actionGet();
		logger.info("Finished deleting index " + IndexName.BOOK_INDEX.value());
	}
}
