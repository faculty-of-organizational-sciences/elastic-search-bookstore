package rs.ac.bg.fon.ai.books.services;

import java.util.LinkedList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;

import rs.ac.bg.fon.ai.books.indexing.ESIndex;
import rs.ac.bg.fon.ai.elasticsearch.ElasticClient;

public class BookstoreSearchServiceImpl implements BookstoreSearchService {
	
	/* (non-Javadoc)
	 * @see rs.ac.bg.fon.ai.bookstore.services.search.BookstoreSearchService#retrieveAllBooks()
	 */
	@Override
	public List<Long> retrieveAllBooks() {
		SearchResponse searchResponse = ElasticClient.getInstance().getClient()
				.prepareSearch(ESIndex.BOOK.name().toLowerCase())	// multiple indexes can be listed here
				.setTypes(ESIndex.BOOK.getIndexName())	// multiple types can be listed here
				.execute().actionGet();
		
		List<Long> ids = new LinkedList<>();
		
		for (SearchHit hit : searchResponse.getHits()) {
			ids.add(Long.parseLong(hit.getId()));
		}

		return ids;
	}

	/* (non-Javadoc)
	 * @see rs.ac.bg.fon.ai.bookstore.services.search.BookstoreSearchService#retrieveBooksFromYear(long)
	 */
	@Override
	public List<Long> retrieveBooksFromYear(long year) {
		QueryBuilder qb = QueryBuilders.termQuery("year", year);
		
		SearchResponse searchResponse = ElasticClient.getInstance().getClient()
				.prepareSearch(ESIndex.BOOK.name().toLowerCase())	// multiple indexes can be listed here
				.setTypes(ESIndex.BOOK.getIndexName())	// multiple types can be listed here
				.setQuery(qb)
				.execute().actionGet();
		
		List<Long> ids = new LinkedList<>();
		
		for (SearchHit hit : searchResponse.getHits()) {
			ids.add(Long.parseLong(hit.getId()));
		}

		return ids;
	}
	
	/* (non-Javadoc)
	 * @see rs.ac.bg.fon.ai.bookstore.services.search.BookstoreSearchService#retrieveBooksFromYearAndBefore(long)
	 */
	@Override
	public List<Long> retrieveBooksFromYearAndBefore(long year) {
		QueryBuilder qb = QueryBuilders.rangeQuery("year")
				.lte(year);
		
		SearchResponse searchResponse = ElasticClient.getInstance().getClient()
				.prepareSearch(ESIndex.BOOK.name().toLowerCase())	// multiple indexes can be listed here
				.setTypes(ESIndex.BOOK.getIndexName())	// multiple types can be listed here
				.setQuery(qb)			
				.execute().actionGet();
		
		List<Long> ids = new LinkedList<>();
		
		for (SearchHit hit : searchResponse.getHits()) {
			ids.add(Long.parseLong(hit.getId()));
		}

		return ids;
	}
	
	/* (non-Javadoc)
	 * @see rs.ac.bg.fon.ai.bookstore.services.search.BookstoreSearchService#retrieveBooksFromYearAndBeforeSorted(long)
	 */
	@Override
	public List<Long> retrieveBooksFromYearAndBeforeSorted(long year) {
		QueryBuilder qb = QueryBuilders.rangeQuery("year")
				.lte(year);
		
		SearchResponse searchResponse = ElasticClient.getInstance().getClient()
				.prepareSearch(ESIndex.BOOK.name().toLowerCase())	// multiple indexes can be listed here
				.setTypes(ESIndex.BOOK.getIndexName())	// multiple types can be listed here
				.setQuery(qb)
				.addSort("score", SortOrder.DESC)
				.execute().actionGet();
		
		List<Long> ids = new LinkedList<>();
		
		for (SearchHit hit : searchResponse.getHits()) {
			ids.add(Long.parseLong(hit.getId()));
		}

		return ids;
	}
	
	/* (non-Javadoc)
	 * @see rs.ac.bg.fon.ai.bookstore.services.search.BookstoreSearchService#retrieveBooksFromYearAndBeforeWithPagination(long, int, int)
	 */
	@Override
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
		
		List<Long> ids = new LinkedList<>();
		
		for (SearchHit hit : searchResponse.getHits()) {
			ids.add(Long.parseLong(hit.getId()));
		}

		return ids;
	}
	
	/* (non-Javadoc)
	 * @see rs.ac.bg.fon.ai.bookstore.services.search.BookstoreSearchService#retrieveMaleAuthors()
	 */
	@Override
	public List<Long> retrieveMaleAuthors() {
		QueryBuilder qb = QueryBuilders.matchQuery("gender", "MALE");
		
		SearchResponse searchResponse = ElasticClient.getInstance().getClient()
				.prepareSearch(ESIndex.AUTHOR.name().toLowerCase())	// multiple indexes can be listed here
				.setTypes(ESIndex.AUTHOR.getIndexName())	// multiple types can be listed here
				.setQuery(qb)
				.execute().actionGet();
		
		List<Long> ids = new LinkedList<>();
		
		for (SearchHit hit : searchResponse.getHits()) {
			ids.add(Long.parseLong(hit.getId()));
		}

		return ids;
	}

	/* (non-Javadoc)
	 * @see rs.ac.bg.fon.ai.bookstore.services.search.BookstoreSearchService#searchBooksHavingAnyWordInTitle(java.lang.String)
	 */
	@Override
	public List<Long> searchBooksHavingAnyWordInTitle(String query) {
		QueryBuilder qb = QueryBuilders.queryStringQuery(query)
				.defaultField("title");
		
		SearchResponse searchResponse = ElasticClient.getInstance().getClient()
				.prepareSearch(ESIndex.BOOK.name().toLowerCase())	// multiple indexes can be listed here
				.setTypes(ESIndex.BOOK.getIndexName())	// multiple types can be listed here
				.setQuery(qb)
				.execute().actionGet();
		
		List<Long> ids = new LinkedList<>();
		
		for (SearchHit hit : searchResponse.getHits()) {
			ids.add(Long.parseLong(hit.getId()));
		}

		return ids;
	}
	
	/* (non-Javadoc)
	 * @see rs.ac.bg.fon.ai.bookstore.services.search.BookstoreSearchService#searchBooksHavingAllWordsInTitle(java.lang.String)
	 */
	@Override
	public List<Long> searchBooksHavingAllWordsInTitle(String query) {
		QueryBuilder qb = QueryBuilders.queryStringQuery(query)
				.defaultField("title")
				.defaultOperator(Operator.AND);
		
		SearchResponse searchResponse = ElasticClient.getInstance().getClient()
				.prepareSearch(ESIndex.BOOK.name().toLowerCase())	// multiple indexes can be listed here
				.setTypes(ESIndex.BOOK.getIndexName())	// multiple types can be listed here
				.setQuery(qb)
				.execute().actionGet();
		
		List<Long> ids = new LinkedList<>();
		
		for (SearchHit hit : searchResponse.getHits()) {
			ids.add(Long.parseLong(hit.getId()));
		}

		return ids;
	}
	
	/* (non-Javadoc)
	 * @see rs.ac.bg.fon.ai.bookstore.services.search.BookstoreSearchService#searchBooks(java.lang.String)
	 */
	@Override
	public List<Long> searchBooks(String query) {
		QueryBuilder qb = QueryBuilders.queryStringQuery(query + "*")
				.defaultField("title")
				.defaultOperator(Operator.AND);
		
		SearchResponse searchResponse = ElasticClient.getInstance().getClient()
				.prepareSearch(ESIndex.BOOK.name().toLowerCase())	// multiple indexes can be listed here
				.setTypes(ESIndex.BOOK.getIndexName())	// multiple types can be listed here
				.setQuery(qb)
				.execute().actionGet();
		
		List<Long> ids = new LinkedList<>();
		
		for (SearchHit hit : searchResponse.getHits()) {
			ids.add(Long.parseLong(hit.getId()));
		}
		
		return ids;
	}
	
	/* (non-Javadoc)
	 * @see rs.ac.bg.fon.ai.bookstore.services.search.BookstoreSearchService#fuzzySearchBooks(java.lang.String)
	 */
	@Override
	public List<Long> fuzzySearchBooks(String query) {
		QueryBuilder qb = QueryBuilders.fuzzyQuery("title", query);
		
		SearchResponse searchResponse = ElasticClient.getInstance().getClient()
				.prepareSearch(ESIndex.BOOK.name().toLowerCase())	// multiple indexes can be listed here
				.setTypes(ESIndex.BOOK.getIndexName())	// multiple types can be listed here
				.setQuery(qb)
				.execute().actionGet();
		
		List<Long> ids = new LinkedList<>();
		
		for (SearchHit hit : searchResponse.getHits()) {
			ids.add(Long.parseLong(hit.getId()));
		}

		return ids;
	}

	/* (non-Javadoc)
	 * @see rs.ac.bg.fon.ai.bookstore.services.search.BookstoreSearchService#searchForBooksOfAuthor(long, java.lang.String)
	 */
	@Override
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
		
		List<Long> ids = new LinkedList<>();
		
		for (SearchHit hit : searchResponse.getHits()) {
			ids.add(Long.parseLong(hit.getId()));
		}

		return ids;
	}
}
