package rs.ac.bg.fon.ai.books.indexing;

import java.io.IOException;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import rs.ac.bg.fon.ai.elasticsearch.ElasticsearchAdministration;

public class BookstoreIndexAdministration {
	
	private static final Logger logger = LogManager.getLogger(BookstoreIndexAdministration.class);
	
	private ElasticsearchAdministration esAdmin = new ElasticsearchAdministration();

	public void createAllIndices() {
		boolean success = true;
		for (ESIndex index : ESIndex.values()) {
			try {
				String indexName = index.getIndexName();
				String mappings  = ElasticsearchAdministration.copyToStringFromClasspath("/rs/ac/bg/fon/ai/bookstore/es/mappings/" + indexName + "-mapping.json");
				boolean createSuccess = esAdmin.createIndex(indexName, mappings);
				
				success = success && createSuccess;
			} catch (IOException e) {
				logger.error("Error", e);
			}
		}
		
		if (success)
			logger.info("All indices are created successfully");
		else
			logger.error("There was a problem creating indices");
	}
	
	public void deleteAllIndices() {
		String[] indices = Arrays.stream(ESIndex.values()).map(i -> i.getIndexName()).toArray(String[]::new);
		
		boolean success = esAdmin.deleteIndices(indices);
		
		if (success)
			logger.info("All indices are delete successfully");
		else
			logger.error("There was a problem deleting indices");
	}

}
