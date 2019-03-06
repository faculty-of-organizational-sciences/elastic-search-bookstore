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
		

		return null;
	}

	/* (non-Javadoc)
	 * @see rs.ac.bg.fon.ai.bookstore.services.search.BookstoreSearchService#retrieveBooksFromYear(long)
	 */
	@Override
	public List<Long> retrieveBooksFromYear(long year) {

		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see rs.ac.bg.fon.ai.bookstore.services.search.BookstoreSearchService#retrieveBooksFromYearAndBefore(long)
	 */
	@Override
	public List<Long> retrieveBooksFromYearAndBefore(long year) {
		

		return null;
	}
	
	/* (non-Javadoc)
	 * @see rs.ac.bg.fon.ai.bookstore.services.search.BookstoreSearchService#retrieveBooksFromYearAndBeforeSorted(long)
	 */
	@Override
	public List<Long> retrieveBooksFromYearAndBeforeSorted(long year) {
		

		return null;
	}
	
	/* (non-Javadoc)
	 * @see rs.ac.bg.fon.ai.bookstore.services.search.BookstoreSearchService#retrieveBooksFromYearAndBeforeWithPagination(long, int, int)
	 */
	@Override
	public List<Long> retrieveBooksFromYearAndBeforeWithPagination(long year, int limit, int page) {
		

		return null;
	}
	
	/* (non-Javadoc)
	 * @see rs.ac.bg.fon.ai.bookstore.services.search.BookstoreSearchService#retrieveMaleAuthors()
	 */
	@Override
	public List<Long> retrieveMaleAuthors() {
		

		return null;
	}

	/* (non-Javadoc)
	 * @see rs.ac.bg.fon.ai.bookstore.services.search.BookstoreSearchService#searchBooksHavingAnyWordInTitle(java.lang.String)
	 */
	@Override
	public List<Long> searchBooksHavingAnyWordInTitle(String query) {
		

		return null;
	}
	
	/* (non-Javadoc)
	 * @see rs.ac.bg.fon.ai.bookstore.services.search.BookstoreSearchService#searchBooksHavingAllWordsInTitle(java.lang.String)
	 */
	@Override
	public List<Long> searchBooksHavingAllWordsInTitle(String query) {
		

		return null;
	}
	
	/* (non-Javadoc)
	 * @see rs.ac.bg.fon.ai.bookstore.services.search.BookstoreSearchService#searchBooks(java.lang.String)
	 */
	@Override
	public List<Long> searchBooks(String query) {
		
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see rs.ac.bg.fon.ai.bookstore.services.search.BookstoreSearchService#fuzzySearchBooks(java.lang.String)
	 */
	@Override
	public List<Long> fuzzySearchBooks(String query) {
		

		return null;
	}

	/* (non-Javadoc)
	 * @see rs.ac.bg.fon.ai.bookstore.services.search.BookstoreSearchService#searchForBooksOfAuthor(long, java.lang.String)
	 */
	@Override
	public List<Long> searchForBooksOfAuthor(long authorId, String query) {
		

		return null;
	}
}
