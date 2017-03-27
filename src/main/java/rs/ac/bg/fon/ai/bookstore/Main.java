package rs.ac.bg.fon.ai.bookstore;

import java.util.List;

import rs.ac.bg.fon.ai.bookstore.services.BookstorePersistance;
import rs.ac.bg.fon.ai.bookstore.services.search.SearchService;

public class Main {

	public static void main(String[] args) {
		BookstorePersistance persistance = new BookstorePersistance();
		
//		BookIndexer bIndexer = new BookIndexer();
//		bIndexer.indexBooks(persistance.loadAllBooks());
//		
//		AuthorIndexer aIndexer = new AuthorIndexer();
//		aIndexer.indexAuthors(persistance.loadAllAuthors());
		
		SearchService search = new SearchService();
		
		System.out.println("Searching for all books having 'Beauty' in their title");
		List<Long> books1 = search.searchBooks("The", 10, 1);
		
		for (Long id : books1) {
			System.out.println(persistance.getBookById(id));
		}
		
		System.out.println("Searching for all authors starting with 'm' in their name or lastname");
		List<Long> authors = search.searchAuthors("m", 10, 1);
		
		for (Long id : authors) {
			System.out.println(persistance.getAuthorById(id));
		}
		
		System.out.println("Searching for authors having 'History' in title of their book");
		List<Long> authors1 = search.searchAuthorsWithBookTitle("History", 10, 1);
		
		for (Long id : authors1) {
			System.out.println(persistance.getAuthorById(id));
		}	
	}

}
