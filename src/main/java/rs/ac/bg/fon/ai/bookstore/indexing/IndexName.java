package rs.ac.bg.fon.ai.bookstore.indexing;

public enum IndexName {

	BOOK_INDEX ("book"),
	AUTHOR_INDEX ("author");
	
	private String value;
	
	IndexName(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
}
