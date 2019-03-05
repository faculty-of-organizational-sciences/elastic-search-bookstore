package rs.ac.bg.fon.ai.bookstore.indexing.administration;

public enum ESIndex {

	BOOK ("novel"),
	AUTHOR ("author");
	
	private String indexName;
	
	ESIndex(String indexName) {
		this.indexName = indexName;
	}

	public String getIndexName() {
		return indexName;
	}
	
}
