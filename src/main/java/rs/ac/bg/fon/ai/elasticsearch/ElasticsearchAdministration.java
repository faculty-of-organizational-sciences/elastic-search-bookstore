package rs.ac.bg.fon.ai.elasticsearch;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

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

	public boolean createIndex(String indexName, String mappings) {
		indexName = indexName.toLowerCase();
		
		Client client = ElasticClient.getInstance().getClient();
		
		boolean exists = client.admin().indices().prepareExists(indexName)
				.execute().actionGet().isExists();

		if (!exists) {
			client.admin().indices().prepareCreate(indexName).get();
			
			AcknowledgedResponse response = client.admin().indices().preparePutMapping(indexName.toLowerCase())   
		        .setType(indexName)	// we use the same
		        .setSource(mappings, XContentType.JSON)
		        .get();
			
			return response.isAcknowledged();
		}
		return false;
	}

	public boolean deleteIndices(String[] indices) {
		try {
			Client client = ElasticClient.getInstance().getClient();
		
			AcknowledgedResponse response = client.admin().indices().delete(new DeleteIndexRequest(indices)).actionGet();
			
			return response.isAcknowledged();
		} catch (IndexNotFoundException e) {
			logger.error("Index does not exist so it can't be deleted", e);
			return false;
		}
	}

	public static String copyToStringFromClasspath(String path) throws IOException {
        InputStream is = Streams.class.getResourceAsStream(path);
        if (is == null) {
            throw new FileNotFoundException("Resource [" + path + "] not found in classpath");
        }
        return Streams.copyToString(new InputStreamReader(is, Charset.defaultCharset()));
    }
}
