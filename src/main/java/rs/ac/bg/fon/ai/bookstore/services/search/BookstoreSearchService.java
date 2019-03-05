package rs.ac.bg.fon.ai.bookstore.services.search;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;

import rs.ac.bg.fon.ai.bookstore.indexing.administration.ESIndex;
import rs.ac.bg.fon.ai.bookstore.indexing.administration.ElasticClient;

public class BookstoreSearchService {

	/**
	 * Retrieves all books published in a provided year.
	 * 
	 * @param year
	 * @return book ids
	 */
	public List<Long> retrieveBooksFromYear(long year) {
		QueryBuilder qb = QueryBuilders.termQuery("year", year);
		
		SearchResponse searchResponse = ElasticClient.getInstance().getClient()
				.prepareSearch(ESIndex.BOOK.name().toLowerCase())	// multiple indexes can be listed here
				.setTypes(ESIndex.BOOK.getIndexName())	// multiple types can be listed here
				.setQuery(qb)
				.execute().actionGet();
		
		List<Long> ids = new ArrayList<Long>();
		
		for (SearchHit hit : searchResponse.getHits()) {
			ids.add(Long.parseLong(hit.getId()));
		}

		return ids;
	}
	
	/**
	 * Retrieves all books published in a provided year or before.
	 * 
	 * @param year
	 * @return book ids
	 */
	public List<Long> retrieveBooksFromYearAndBefore(long year) {
		QueryBuilder qb = QueryBuilders.rangeQuery("year")
				.lte(year);
		
		SearchResponse searchResponse = ElasticClient.getInstance().getClient()
				.prepareSearch(ESIndex.BOOK.name().toLowerCase())	// multiple indexes can be listed here
				.setTypes(ESIndex.BOOK.getIndexName())	// multiple types can be listed here
				.setQuery(qb)			
				.execute().actionGet();
		
		List<Long> ids = new ArrayList<Long>();
		
		for (SearchHit hit : searchResponse.getHits()) {
			ids.add(Long.parseLong(hit.getId()));
		}

		return ids;
	}
	
	/**
	 * Retrieves all books published in a provided year or before sorted.
	 * 
	 * @param year
	 * @return book ids
	 */
	public List<Long> retrieveBooksFromYearAndBeforeSorted(long year) {
		QueryBuilder qb = QueryBuilders.rangeQuery("year")
				.lte(year);
		
		SearchResponse searchResponse = ElasticClient.getInstance().getClient()
				.prepareSearch(ESIndex.BOOK.name().toLowerCase())	// multiple indexes can be listed here
				.setTypes(ESIndex.BOOK.getIndexName())	// multiple types can be listed here
				.setQuery(qb)
				.addSort("score", SortOrder.DESC)
				.execute().actionGet();
		
		List<Long> ids = new ArrayList<Long>();
		
		for (SearchHit hit : searchResponse.getHits()) {
			ids.add(Long.parseLong(hit.getId()));
		}

		return ids;
	}
	
	/**
	 * Retrieves all books published in a provided year or before paginated.
	 * 
	 * @param year
	 * @param limit
	 * @param page
	 * @return book ids
	 */
	public List<Long> retrieveBooksFromYearAndBeforeWithPagination(long year, int limit, int page) {
		int offset = (page - 1) * limit;
		
		QueryBuilder qb = QueryBuilders.rangeQuery("year")
				.lte(year);
		
		SearchResponse searchResponse = ElasticClient.getInstance().getClient()
				.prepareSearch(ESIndex.BOOK.name().toLowerCase())	// multiple indexes can be listed here
				.setTypes(ESIndex.BOOK.getIndexName())	// multiple types can be listed here
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
	
	/**
	 * Retrieves all male authors.
	 * 
	 * @return author ids
	 */
	public List<Long> retrieveMaleAuthors() {
		QueryBuilder qb = QueryBuilders.matchQuery("gender", "MALE");
		
		SearchResponse searchResponse = ElasticClient.getInstance().getClient()
				.prepareSearch(ESIndex.AUTHOR.name().toLowerCase())	// multiple indexes can be listed here
				.setTypes(ESIndex.AUTHOR.getIndexName())	// multiple types can be listed here
				.setQuery(qb)
				.execute().actionGet();
		
		List<Long> ids = new ArrayList<Long>();
		
		for (SearchHit hit : searchResponse.getHits()) {
			ids.add(Long.parseLong(hit.getId()));
		}

		return ids;
	}

	/**
	 * Searching for books having any of the provided words from the query parameter in their title.
	 * 
	 * @param query
	 * @return book ids
	 */
	public List<Long> searchBooksHavingAnyWordInTitle(String query) {
		QueryBuilder qb = QueryBuilders.queryStringQuery(query)
				.defaultField("title");
		
		SearchResponse searchResponse = ElasticClient.getInstance().getClient()
				.prepareSearch(ESIndex.BOOK.name().toLowerCase())	// multiple indexes can be listed here
				.setTypes(ESIndex.BOOK.getIndexName())	// multiple types can be listed here
				.setQuery(qb)
				.execute().actionGet();
		
		List<Long> ids = new ArrayList<Long>();
		
		for (SearchHit hit : searchResponse.getHits()) {
			ids.add(Long.parseLong(hit.getId()));
		}

		return ids;
	}
	
	/**
	 * Searching for books having all of the provided words from the query parameter in their title.
	 * 
	 * @param query
	 * @return book ids
	 */
	public List<Long> searchBooksHavingAllWordsInTitle(String query) {
		QueryBuilder qb = QueryBuilders.queryStringQuery(query)
				.defaultField("title")
				.defaultOperator(Operator.AND);
		
		SearchResponse searchResponse = ElasticClient.getInstance().getClient()
				.prepareSearch(ESIndex.BOOK.name().toLowerCase())	// multiple indexes can be listed here
				.setTypes(ESIndex.BOOK.getIndexName())	// multiple types can be listed here
				.setQuery(qb)
				.execute().actionGet();
		
		List<Long> ids = new ArrayList<Long>();
		
		for (SearchHit hit : searchResponse.getHits()) {
			ids.add(Long.parseLong(hit.getId()));
		}

		return ids;
	}
	
	/**
	 * Searching for books having query string in their title.
	 * 
	 * @param query
	 * @return book ids
	 */
	public List<Long> searchBooks(String query) {
		QueryBuilder qb = QueryBuilders.queryStringQuery(query + "*")
				.defaultField("title")
				.defaultOperator(Operator.AND);
		
		SearchResponse searchResponse = ElasticClient.getInstance().getClient()
				.prepareSearch(ESIndex.BOOK.name().toLowerCase())	// multiple indexes can be listed here
				.setTypes(ESIndex.BOOK.getIndexName())	// multiple types can be listed here
				.setQuery(qb)
				.execute().actionGet();
		
		List<Long> ids = new ArrayList<Long>();
		
		for (SearchHit hit : searchResponse.getHits()) {
			ids.add(Long.parseLong(hit.getId()));
		}
		
		return ids;
	}
	
	/**
	 * Fuzzy search books by title
	 * 
	 * @param query
	 * @return book ids
	 */
	public List<Long> fuzzySearchBooks(String query) {
		QueryBuilder qb = QueryBuilders.fuzzyQuery("title", query);
		
		SearchResponse searchResponse = ElasticClient.getInstance().getClient()
				.prepareSearch(ESIndex.BOOK.name().toLowerCase())	// multiple indexes can be listed here
				.setTypes(ESIndex.BOOK.getIndexName())	// multiple types can be listed here
				.setQuery(qb)
				.execute().actionGet();
		
		List<Long> ids = new ArrayList<Long>();
		
		for (SearchHit hit : searchResponse.getHits()) {
			ids.add(Long.parseLong(hit.getId()));
		}

		return ids;
	}

	/**
	 * Searches within all books of a given author.
	 * 
	 * @param authorId author whose books to search for
	 * @param query
	 * @return book ids
	 */
	public List<Long> searchForBooksOfAuthor(long authorId, String query) {
		BoolQueryBuilder booleanQuery = QueryBuilders.boolQuery();
		
		QueryBuilder titleQuery = QueryBuilders.queryStringQuery(query + "*")
				.defaultField("title")
				.defaultOperator(Operator.AND);
		
		QueryBuilder authorIdQuery = QueryBuilders.termQuery("authors.id", authorId);
		
		booleanQuery.must(titleQuery);
		booleanQuery.must(authorIdQuery);

		SearchResponse searchResponse = ElasticClient.getInstance().getClient()
				.prepareSearch(ESIndex.BOOK.name().toLowerCase())
				.setTypes(ESIndex.BOOK.getIndexName())
				.setQuery(booleanQuery)
				.execute().actionGet();
		
		List<Long> ids = new ArrayList<Long>();
		
		for (SearchHit hit : searchResponse.getHits()) {
			ids.add(Long.parseLong(hit.getId()));
		}

		return ids;
	}
}
