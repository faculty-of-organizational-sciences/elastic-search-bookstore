package rs.ac.bg.fon.ai.bookstore.indexing;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import rs.ac.bg.fon.ai.bookstore.domain.Author;
import rs.ac.bg.fon.ai.bookstore.domain.Book;

public class AuthorIndexer {
	
	public void indexAuthors(List<Author> authors) {
		System.out.println("Indexing authors");
		
		for (Author author : authors) {
			indexAuthor(author);
		}
	}

	public void indexAuthor(Author author) {
		System.out.println("Indexing author " + author);
		
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
			
			@SuppressWarnings("unused")
			IndexResponse response = ElasticClient.getInstance().getClient().prepareIndex(IndexName.AUTHOR_INDEX.value(), IndexType.AUTHOR.name(), String.valueOf(author.getId()))
			        .setSource(builder)
			        .get();
		} catch (IOException e) {
			System.err.println(e);
		}
	}
	
	public void deleteAuthorIndexes(){
		ElasticClient.getInstance().getClient().admin().indices().delete(Requests.deleteIndexRequest(IndexName.AUTHOR_INDEX.name()));
	}
}
