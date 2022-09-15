package hh.bookstore.bookstore;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.bookstore.bookstore.domain.Book;
import hh.bookstore.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
		Book a = new Book("Kirja 1", "Kirjoittaja", 1997, "1234", 20);
		Book b = new Book("Kirja 2", "Kirjoittaja2", 1998, "12345", 20);
		Book c = new Book("Kirja 3", "Kirjoittaja3", 1999, "12346", 20);
		
		repository.save(a);
		repository.save(b);
		repository.save(c);

		};
	}

}
