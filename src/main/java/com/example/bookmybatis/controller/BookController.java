package com.example.bookmybatis.controller;

import com.example.bookmybatis.domain.Book;
import com.example.bookmybatis.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping(value = "/books")
    public String list(Model model) {
        System.out.println("*** books mapping ***");
        List<Book.Simple> books = bookService.findBooks();
        model.addAttribute("books", books);
        return "books/bookList";
    }

    @GetMapping(value = "/books/new")
    public String createForm() {
        return "books/inputBookForm";
    }

    @PostMapping(value = "/books/new")
    public String create(Book.Create form) {
        bookService.addBook(form);
        return "redirect:/";
    }

    @GetMapping(value = "/books/search")
    public String searchForm() {
        return "books/searchBookForm";
    }

    @PostMapping(value = "/books/search")
    public String search(Book.Create form, Model model) {
        List<Book.Simple> books = bookService.findCondBooks(form);
        model.addAttribute("books", books);
        return "books/bookList";
    }

}
