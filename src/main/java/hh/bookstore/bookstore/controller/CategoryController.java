package hh.bookstore.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.bookstore.bookstore.domain.Category;
import hh.bookstore.bookstore.domain.CategoryRepository;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryRepository repository;

	@RequestMapping(value="/categorylist", method=RequestMethod.GET)
    public String categoryList(Model model) {
		model.addAttribute("categories", repository.findAll());
		return "categorylist";
	}
	
	@RequestMapping(value="/deletecategory/{id}", method=RequestMethod.GET)
    public String deleteCategory(@PathVariable("id") Long categoryId, Model model) {
		repository.deleteById(categoryId);
		return "redirect:../categorylist";
	}
		
	//Add new category
	@RequestMapping(value="/addcategory", method=RequestMethod.GET)
    public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "add-category";
	}
	
	//Save new category
	@RequestMapping(value="/savecategory", method=RequestMethod.POST)
    public String saveCategory(Category category) {
		repository.save(category);
		return "redirect:categorylist";
	}
}

