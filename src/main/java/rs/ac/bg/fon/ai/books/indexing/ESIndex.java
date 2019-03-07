package rs.ac.bg.fon.ai.books.indexing;

public enum ESIndex {

	BOOK ("book"),
	AUTHOR ("author");
	
	private String indexName;
	
	ESIndex(String indexName) {
		this.indexName = indexName;
	}

	public String getIndexName() {
		return indexName;
	}
	
}
