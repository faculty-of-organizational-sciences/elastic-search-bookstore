package rs.ac.bg.fon.ai.bookstore.indexing;

public enum IndexType {

	BOOK ("book"),
	AUTHOR ("author");
	
	private String value;
	
	IndexType(String value) {
		this.value = value;
	}
	
	public String value() {
		return value;
	}
}
