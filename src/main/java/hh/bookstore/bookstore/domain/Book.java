package hh.bookstore.bookstore.domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String title;
	private String author;
	private int releaseyear;
	private String isbn;
	private int price;
	
	@ManyToOne
	@JoinColumn(name = "categoryid")
	private Category category;
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Book() {
	}
	
	public Book(String title, String author, int releaseyear, String isbn, int price, Category category) {
		super();
		this.title = title;
		this.author = author;
		this.releaseyear = releaseyear;
		this.isbn = isbn;
		this.price = price;
		this.category = category;
	}
	
	//id
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	//title
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	//author
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	//ISBN
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	//price
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	//year
	public int getReleaseyear() {
		return releaseyear;
	}
	public void setReleaseyear(int releaseyear) {
		this.releaseyear = releaseyear;
	}
	
	@Override
	public String toString(){
	      return "title: "+this.title+", author: "+ this.author + ", Category: " + this.category + ", id: " + this.id;
	}

}
