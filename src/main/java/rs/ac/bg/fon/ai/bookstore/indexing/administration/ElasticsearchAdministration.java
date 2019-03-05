package rs.ac.bg.fon.ai.bookstore.indexing.administration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.io.Streams;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.IndexNotFoundException;

public class ElasticsearchAdministration {
	
	private static final Logger logger = LogManager.getLogger(ElasticsearchAdministration.class);

	public void createAllIndexes() {
		for (ESIndex index : ESIndex.values()) {
			createIndex(index.getIndexName());
		}
	}
	
	private void createIndex(String indexName) {
		indexName = indexName.toLowerCase();
		
		Client client = ElasticClient.getInstance().getClient();
		
		boolean exists = client.admin().indices().prepareExists(indexName)
				.execute().actionGet().isExists();

		if (!exists) {
			client.admin().indices().prepareCreate(indexName).get();
			
			try {
				String mappingContent = copyToStringFromClasspath("/rs/ac/bg/fon/ai/bookstore/es/mappings/" + indexName + "-mapping.json");
				
				client.admin().indices().preparePutMapping(indexName.toLowerCase())   
			        .setType(indexName)                                
			        .setSource(mappingContent, XContentType.JSON)
			        .get();
			} catch (IOException e) {
				logger.error("Error", e);
			}
		}
	}
	
	private static String copyToStringFromClasspath(String path) throws IOException {
        InputStream is = Streams.class.getResourceAsStream(path);
        if (is == null) {
            throw new FileNotFoundException("Resource [" + path + "] not found in classpath");
        }
        return Streams.copyToString(new InputStreamReader(is, Charset.defaultCharset()));
    }
	
	public boolean deleteAllIndexes() {
		try {
			Client client = ElasticClient.getInstance().getClient();
			String[] indexArray = Arrays.stream(ESIndex.values()).map(i -> i.getIndexName()).toArray(String[]::new);
			AcknowledgedResponse response = client.admin().indices().delete(new DeleteIndexRequest(indexArray)).actionGet();
			
			return response.isAcknowledged();
		} catch (IndexNotFoundException e) {
			logger.error("Index does not exist so it can't be deleted", e);
			return false;
		}
	}

}
