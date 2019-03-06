package rs.ac.bg.fon.ai.books.indexing;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import rs.ac.bg.fon.ai.books.domain.Author;
import rs.ac.bg.fon.ai.elasticsearch.ElasticClient;

public class AuthorIndexer {
	
	private static final Logger logger = LogManager.getLogger(AuthorIndexer.class);
	
	public void indexAuthors(List<Author> authors) {
		logger.debug("Indexing authors");
		
		for (Author author : authors) {
			indexAuthor(author);
		}
	}

	public void indexAuthor(Author author) {
		logger.debug("Indexing author " + author);
		
		
	}
}
