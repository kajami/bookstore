package hh.bookstore.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.bookstore.bookstore.domain.User;
import hh.bookstore.bookstore.domain.Book;
import hh.bookstore.bookstore.domain.BookRepository;
import hh.bookstore.bookstore.domain.Category;
import hh.bookstore.bookstore.domain.CategoryRepository;
import hh.bookstore.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository brepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
		log.info("Save a couple of books and categories");
		Category scifi = new Category("Scifi");
		Category comic = new Category("Comic");
		Category horror = new Category("Horror");
		
		crepository.save(scifi);
		crepository.save(comic);
		crepository.save(horror);
		
		Book a = new Book("Kirja 1", "Kirjoittaja", 1997, "1234", 20, scifi);
		Book b = new Book("Kirja 2", "Kirjoittaja2", 1998, "12345", 20, comic);
		Book c = new Book("Kirja 3", "Kirjoittaja3", 1999, "12346", 20, horror);
		
		// Create users: admin/admin user/user
		User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
		User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
		urepository.save(user1);
		urepository.save(user2);
		
		brepository.save(a);
		brepository.save(b);
		brepository.save(c);
		
		log.info("fetch all books");
		for (Book book: brepository.findAll()) {
			log.info(book.toString());
		}
		
		log.info("fetch all categories");
		for (Category category: crepository.findAll()) {
			log.info(category.toString());
		}

		};
	}

}
