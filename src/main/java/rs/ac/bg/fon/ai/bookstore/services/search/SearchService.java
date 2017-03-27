package rs.ac.bg.fon.ai.bookstore.services.search;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import rs.ac.bg.fon.ai.bookstore.indexing.ElasticClient;
import rs.ac.bg.fon.ai.bookstore.indexing.IndexName;
import rs.ac.bg.fon.ai.bookstore.indexing.IndexType;

public class SearchService {

	public List<Long> searchBooks(String query, int limit, int page) {
		int offset = (page - 1) * limit;
		QueryBuilder qb;

		if (query == "") {
			qb = QueryBuilders.matchAllQuery();
		} else {
			qb = QueryBuilders.queryStringQuery(query + "*");
		}

		SearchResponse searchResponse = ElasticClient.getInstance().getClient()
				.prepareSearch(IndexName.BOOK_INDEX.value())
				.setTypes(IndexType.BOOK.name())
				.setQuery(qb)
				.setFrom(offset)
				.setSize(limit)
				.execute().actionGet();
		
		List<Long> ids = new ArrayList<Long>();
		
		for (SearchHit hit : searchResponse.getHits()) {
			ids.add(Long.parseLong(hit.getId()));
		}

		return ids;
	}

	public List<Long> searchAuthors(String query, int limit, int page) {
		int offset = (page - 1) * limit;
		QueryBuilder qb;

		if (query == "") {
			qb = QueryBuilders.matchAllQuery();
		} else {
			qb = QueryBuilders.queryStringQuery(query + "*")
					.field("name").field("lastname");
		}

		SearchResponse searchResponse = ElasticClient.getInstance().getClient()
				.prepareSearch(IndexName.AUTHOR_INDEX.value())
				.setTypes(IndexType.AUTHOR.name())
				.setQuery(qb)
				.setFrom(offset)
				.setSize(limit)
				.execute().actionGet();
		
		List<Long> ids = new ArrayList<Long>();
		
		for (SearchHit hit : searchResponse.getHits()) {
			ids.add(Long.parseLong(hit.getId()));
		}

		return ids;
	}

	public List<Long> searchAuthorsWithBookTitle(String query, int limit, int page) {
		int offset = (page - 1) * limit;
		QueryBuilder qb;

		if (query == "") {
			qb = QueryBuilders.matchAllQuery();
		} else {
			qb = QueryBuilders.queryStringQuery(query + "*")
					.field("books.title");
		}

		SearchResponse searchResponse = ElasticClient.getInstance().getClient()
				.prepareSearch(IndexName.AUTHOR_INDEX.value())
				.setTypes(IndexType.AUTHOR.name())
				.setQuery(qb)
				.setFrom(offset)
				.setSize(limit)
				.execute().actionGet();
		
		List<Long> ids = new ArrayList<Long>();
		
		for (SearchHit hit : searchResponse.getHits()) {
			ids.add(Long.parseLong(hit.getId()));
		}

		return ids;
	}
}
