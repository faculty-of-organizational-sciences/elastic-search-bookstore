package rs.ac.bg.fon.ai.bookstore.indexing;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

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
		Settings settings = Settings.settingsBuilder()
				.put("cluster.name", clusterName)
				.build();
		
		TransportClient transportClient = TransportClient.builder().settings(settings).build();
		
		try {
			transportClient = transportClient
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(ipAddress), port));
		} catch (UnknownHostException e) {
			return;
		}
		
		client = transportClient;
	}

	public Client getClient() {
		return client;
	}

}
