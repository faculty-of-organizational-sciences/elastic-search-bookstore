package rs.ac.bg.fon.ai.books.domain;

import java.util.LinkedList;
import java.util.List;

public class Book {

	private long id;
	private String title;
	private int year;
	private String isbn;
	private double score;
	
	private List<Author> authors;
	
	public Book() {
		authors = new LinkedList<>();
	}

	public Book(long id, String title, int year, String isbn, double score) {
		this();
		this.id = id;
		this.title = title;
		this.year = year;
		this.isbn = isbn;
		this.score = score;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}
	
	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
	public void addAuthor(Author author) {
		authors.add(author);
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", year=" + year + ", isbn=" + isbn + ", score=" + score + "]";
	}
	
}
