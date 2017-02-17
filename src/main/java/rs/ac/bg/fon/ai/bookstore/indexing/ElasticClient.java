package rs.ac.bg.fon.ai.bookstore.indexing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ElasticClient {
	
	private Logger logger = LogManager.getLogger();
	
	private static ElasticClient INSTANCE = null;
	
	// this is something to put in the app configuration file
	private final String clusterName = "elasticsearch";
	private final String ipAddress = "127.0.0.1";
	private final int port = 9300;

	public static ElasticClient getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ElasticClient();
		}
		return INSTANCE;
	}

	private ElasticClient() {
		// TODO: instantiate client
	}

}
