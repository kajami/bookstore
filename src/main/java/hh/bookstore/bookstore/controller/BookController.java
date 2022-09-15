package hh.bookstore.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.bookstore.bookstore.domain.Book;
import hh.bookstore.bookstore.domain.BookRepository;

@Controller
public class BookController {
		
		@Autowired
		private BookRepository repository;

		@RequestMapping(value="/index", method=RequestMethod.GET)
	    public String getBooks(Model model) {
		return "homepage";
		}
		
		@RequestMapping(value="/booklist", method=RequestMethod.GET)
	    public String bookList(Model model) {
			model.addAttribute("books", repository.findAll());
			return "booklist";
		}
		
		@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
			repository.deleteById(bookId);
			return "redirect:../booklist";
		}
		
		//Update book
		@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	    public String updateBook(@PathVariable("id") long bookId, Model model) {
			model.addAttribute("book", repository.findById(bookId));
			return "update-book";
		}
		
		//Save update
		@RequestMapping(value="/saveupdate", method=RequestMethod.PUT)
	    public String updateBook(@RequestBody Book book, Model model) {
			repository.findById(book.getId()).map(bookToUpdate -> {
				bookToUpdate.setId(book.getId());
				bookToUpdate.setAuthor(book.getAuthor());
				bookToUpdate.setTitle(book.getTitle());
				bookToUpdate.setReleaseyear(book.getReleaseyear());
				bookToUpdate.setIsbn(book.getIsbn());
				bookToUpdate.setPrice(book.getPrice());
                return repository.save(bookToUpdate);
            });
			return "redirect:booklist";
		}
		
		//Add new book
		@RequestMapping(value="/add", method=RequestMethod.GET)
	    public String addBook(Model model) {
			model.addAttribute("book", new Book());
			return "add-book";
		}
		
		//Save new book
		@RequestMapping(value="/save", method=RequestMethod.POST)
	    public String saveBook(Book book) {
			repository.save(book);
			return "redirect:booklist";
		}

}
