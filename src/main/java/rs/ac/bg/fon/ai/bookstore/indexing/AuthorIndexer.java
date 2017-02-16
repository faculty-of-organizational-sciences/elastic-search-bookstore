package rs.ac.bg.fon.ai.bookstore.indexing;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import rs.ac.bg.fon.ai.bookstore.domain.Author;
import rs.ac.bg.fon.ai.bookstore.domain.Book;

public class AuthorIndexer {
	
	private Logger logger = LogManager.getLogger();
	
	public void indexAuthors(List<Author> authors) {
		logger.info("Indexing authors");
		
		for (Author author : authors) {
			indexAuthor(author);
		}
	}

	public void indexAuthor(Author author) {
		logger.info("Indexing author " + author);
		
		try {
			XContentBuilder builder = XContentFactory.jsonBuilder();
			
			builder.startObject();
			
			builder.field("name", author.getName() != null ? author.getName() : "" );
			builder.field("lastname", author.getLastname() != null ? author.getLastname() : "" );
			
			// index author books
			List<Book> books = author.getBooks();
			
			builder.startArray("books");
			
			for (Book book : books) {
				builder.startObject();
				builder.field("title", book.getTitle() != null ? book.getTitle() : "" );
				builder.endObject();
			}
			builder.endArray();
            
            builder.endObject();
            
            /*
			 * Performing e.g.
			 * PUT http://localhost:9200/author/author/2
			 * 
			 *	{
			 *		"name": "Colleen",
			 *		"lastname": "McCullough",
			 *		"books": [
			 *			{
			 *				"title": "The Thorn Birds"
			 *			}
			 *		]
			 *	}
			 */
			
			@SuppressWarnings("unused")
			IndexResponse response = ElasticClient.getInstance().getClient().prepareIndex(IndexName.AUTHOR_INDEX.value(), IndexType.AUTHOR.value(), String.valueOf(author.getId()))
			        .setSource(builder)
			        .get();
		} catch (IOException e) {
			logger.error(e);
		}
	}
	
	public void deleteIndex(){
		logger.info("Deleting index " + IndexName.AUTHOR_INDEX.value());
		ElasticClient.getInstance().getClient().admin().indices().delete(new DeleteIndexRequest(IndexName.AUTHOR_INDEX.value())).actionGet();
		logger.info("Finished deleting index " + IndexName.AUTHOR_INDEX.value());
	}
}
