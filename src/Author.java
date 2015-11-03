

public class Author {
	public String books;
	public String booksAbout;
	public String influenced;
	public String influencedBy;
	public String name;
	
	public void getValue(JsonUtility json){
		books = json.getProperty("/book/author/works_written", "text");
		booksAbout = json.getProperty("/book/book_subject/works", "text");
		influenced = json.getProperty("/influence/influence_node/influenced", "text");
		influencedBy = json.getProperty("/influence/influence_node/influenced_by", "text");
		name = json.getProperty("/type/object/name", "value");
	}
}
