package com.geetanjali.bookstore.controller;

import com.geetanjali.bookstore.entity.Book;
import com.geetanjali.bookstore.entity.Author;
import com.geetanjali.bookstore.entity.Category;
import com.geetanjali.bookstore.service.BookService;
import com.geetanjali.bookstore.service.AuthorService;
import com.geetanjali.bookstore.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookController(
            BookService bookService,
            AuthorService authorService,
            CategoryService categoryService) {

        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getBooks(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String category,
            Model model) {

        if (search != null && !search.trim().isEmpty()) {

            model.addAttribute(
                    "books",
                    bookService.searchBooks(search)
            );

        } else if (category != null && !category.trim().isEmpty()) {

            model.addAttribute(
                    "books",
                    bookService.findBooksByCategory(category)
            );

        } else {

            model.addAttribute(
                    "books",
                    bookService.getAllBooks()
            );
        }

        model.addAttribute("search", search);
        model.addAttribute("category", category);

        return "book-list";
    }

    @GetMapping("/add")
    public String showAddBookForm(Model model) {

        model.addAttribute("book", new Book());

        model.addAttribute(
                "authors",
                authorService.getAllAuthors()
        );

        model.addAttribute(
                "categories",
                categoryService.getAllCategories()
        );

        return "add-book";
    }

    @PostMapping("/save")
    public String saveBook(
            @ModelAttribute Book book) {

        bookService.saveBook(book);

        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String bookDetails(
            @PathVariable Long id,
            Model model) {

        Book book = bookService
                .getBookById(id)
                .orElseThrow(() ->
                        new RuntimeException("Book not found"));

        model.addAttribute("book", book);

        return "book-details";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(
            @PathVariable Long id) {

        bookService.deleteBook(id);

        return "redirect:/books";
    }
}