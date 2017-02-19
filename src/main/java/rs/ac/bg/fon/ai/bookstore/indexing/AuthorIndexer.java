package rs.ac.bg.fon.ai.bookstore.indexing;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rs.ac.bg.fon.ai.bookstore.domain.Author;

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
		
		// TODO: index author instance
	}
	
	public void deleteIndex(){
		logger.info("Deleting index " + IndexName.AUTHOR_INDEX.value());
		
		// TODO: Delete author index

		logger.info("Finished deleting index " + IndexName.AUTHOR_INDEX.value());
	}
}
