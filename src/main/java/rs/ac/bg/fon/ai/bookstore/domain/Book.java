package rs.ac.bg.fon.ai.bookstore.domain;

public class Book {

	private long id;
	private String title;
	private int year;
	private String isbn;
	
	private Author author;
	
	public Book() { }

	public Book(long id, String title, int year, String isbn) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		this.isbn = isbn;
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

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", year=" + year + ", isbn=" + isbn + ", author=" + author + "]";
	}
	
}
