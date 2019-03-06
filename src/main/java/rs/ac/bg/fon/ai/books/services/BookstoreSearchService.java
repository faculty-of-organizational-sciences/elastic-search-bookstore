package rs.ac.bg.fon.ai.books.services;

import java.util.List;

public interface BookstoreSearchService {
	
	/**
	 * Retrieves all books.
	 * 
	 * @return book ids
	 */
	List<Long> retrieveAllBooks();

	/**
	 * Retrieves all books published in a given year.
	 * 
	 * @param year
	 * @return book ids
	 */
	List<Long> retrieveBooksFromYear(long year);

	/**
	 * Retrieves all books published in a provided year or before.
	 * 
	 * @param year
	 * @return book ids
	 */
	List<Long> retrieveBooksFromYearAndBefore(long year);

	/**
	 * Retrieves all books published in a provided year or before sorted.
	 * 
	 * @param year
	 * @return book ids
	 */
	List<Long> retrieveBooksFromYearAndBeforeSorted(long year);

	/**
	 * Retrieves all books published in a provided year or before paginated.
	 * 
	 * @param year
	 * @param limit
	 * @param page
	 * @return book ids
	 */
	List<Long> retrieveBooksFromYearAndBeforeWithPagination(long year, int limit, int page);

	/**
	 * Retrieves all male authors.
	 * 
	 * @return author ids
	 */
	List<Long> retrieveMaleAuthors();

	/**
	 * Searching for books having any of the provided words from the query parameter in their title.
	 * 
	 * @param query
	 * @return book ids
	 */
	List<Long> searchBooksHavingAnyWordInTitle(String query);

	/**
	 * Searching for books having all of the provided words from the query parameter in their title.
	 * 
	 * @param query
	 * @return book ids
	 */
	List<Long> searchBooksHavingAllWordsInTitle(String query);

	/**
	 * Searching for books having query string in their title.
	 * 
	 * @param query
	 * @return book ids
	 */
	List<Long> searchBooks(String query);

	/**
	 * Fuzzy search books by title
	 * 
	 * @param query
	 * @return book ids
	 */
	List<Long> fuzzySearchBooks(String query);

	/**
	 * Searches within all books of a given author.
	 * 
	 * @param authorId author whose books to search for
	 * @param query
	 * @return book ids
	 */
	List<Long> searchForBooksOfAuthor(long authorId, String query);

}