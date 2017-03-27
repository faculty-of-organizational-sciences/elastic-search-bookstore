package rs.ac.bg.fon.ai.bookstore.indexing;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import rs.ac.bg.fon.ai.bookstore.domain.Book;

public class BookIndexer {
	
	public void indexBooks(List<Book> books) {
		System.out.println("Indexing books");
		
		for (Book book : books) {
			indexBook(book);
		}
	}

	public void indexBook(Book book) {
		System.out.println("Indexing book " + book);
		
		try {
			XContentBuilder builder = XContentFactory.jsonBuilder().startObject();
			
			builder.field("title", book.getTitle() != null ? book.getTitle() : "" );
			
            builder.endObject();
			
			@SuppressWarnings("unused")
			IndexResponse response = ElasticClient.getInstance().getClient().prepareIndex(
						"book_index", 
						"BOOK", 
						String.valueOf(book.getId()))
			        .setSource(builder)
			        .get();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	public void deleteBookIndexes(){
		ElasticClient.getInstance().getClient().admin().indices().delete(Requests.deleteIndexRequest(IndexName.BOOK_INDEX.name()));
	}
}
