package com.example.basicspringbootsample.controller;

import com.example.basicspringbootsample.domain.Book;
import com.example.basicspringbootsample.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    // Search
    @RequestMapping(path = {"/search"})
    public String home(Book book, Model model, String keyword) {
        if(keyword != null) {
            List<Book> listBook = bookService.getByKeyword(keyword);
            model.addAttribute("listBook", listBook);
        } else {
            List<Book> listBook = bookService.listAll();
            model.addAttribute("listBook", listBook);
        }
        return "myBooks";
    }

    @GetMapping("/myBooks")
    public String myBooks(Model model) {
        List<Book> listBook = bookService.listAll();
        model.addAttribute("listBook", listBook);
        return "myBooks";
    }

    @GetMapping("/addBook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @GetMapping("/")
    public String loginPage() {
        return "loginPage";
    }

    @RequestMapping(value = "/saveBook", method = RequestMethod.POST)
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.save(book);
        return "redirect:/myBooks";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditBookPage(@PathVariable(name = "id") int id) {
        //public String showEditStudentPage(@PathVariable(name = "id") int id) {
        ModelAndView mav = new ModelAndView("addBook");
        Book bookObj = bookService.get((long) id);
        mav.addObject("book", bookObj);
        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteBook(@PathVariable(name = "id") int id) {
        bookService.delete((long) id);
        return "redirect:/myBooks";
    }

}
