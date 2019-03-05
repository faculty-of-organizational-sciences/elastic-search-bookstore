package rs.ac.bg.fon.ai.bookstore.indexing.administration;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ElasticClient {

	private static ElasticClient INSTANCE = null;

	private Client client;

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

		Settings settings = Settings.builder().put("cluster.name", clusterName).build();

		try {
			TransportClient transportClient = new PreBuiltTransportClient(settings);
			transportClient.addTransportAddress(new TransportAddress(InetAddress.getByName(ipAddress), port));
			client = transportClient;
		} catch (UnknownHostException e) {
			return;
		}
	}

	public Client getClient() {
		return client;
	}
	
}
