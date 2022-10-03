package hh.bookstore.bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.bookstore.bookstore.domain.Book;
import hh.bookstore.bookstore.domain.BookRepository;
import hh.bookstore.bookstore.domain.CategoryRepository;

@CrossOrigin
@Controller
public class BookController {
		
		@Autowired
		private BookRepository repository;
		
		@Autowired
		private CategoryRepository crepository;

		@RequestMapping(value="/index", method=RequestMethod.GET)
	    public String getBooks(Model model) {
		return "homepage";
		}
		
		@RequestMapping(value="/booklist", method=RequestMethod.GET)
	    public String bookList(Model model) {
			model.addAttribute("books", repository.findAll());
			return "booklist";
		}
		
		// RESTful service to get all books
	    @RequestMapping(value="/books", method = RequestMethod.GET)
	    public @ResponseBody List<Book> bookListRest() {	
	        return (List<Book>) repository.findAll();
	    }
	    
		// RESTful service to get book by id
	    @RequestMapping(value="/books/{id}", method = RequestMethod.GET)
	    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
	    	return repository.findById(bookId);
	    }
	    
	    // RESTful service to save new book
	    @RequestMapping(value="/books", method = RequestMethod.POST)
	    public @ResponseBody Book saveBookRest(@RequestBody Book book) {	
	    	return repository.save(book);
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
			model.addAttribute("categories", crepository.findAll());
			return "add-book";
		}
		
		//Save new book
		@RequestMapping(value="/save", method=RequestMethod.POST)
	    public String saveBook(Book book) {
			repository.save(book);
			return "redirect:booklist";
		}

}
