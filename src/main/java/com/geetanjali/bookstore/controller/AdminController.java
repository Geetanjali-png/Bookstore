package com.geetanjali.bookstore.controller;

import com.geetanjali.bookstore.entity.Book;
import com.geetanjali.bookstore.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final BookRepository bookRepository;

    public AdminController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public String adminDashboard(Model model) {

        model.addAttribute(
                "books",
                bookRepository.findAll()
        );

        return "admin-dashboard";
    }

    @GetMapping("/books/delete/{id}")
    public String deleteBook(@PathVariable Long id) {

        bookRepository.deleteById(id);

        return "redirect:/admin";
    }

    @PostMapping("/books/update-stock/{id}")
    public String updateStock(
            @PathVariable Long id,
            @RequestParam int stock) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Book not found"));

        book.setStock(stock);

        book.setAvailable(stock > 0);

        bookRepository.save(book);

        return "redirect:/admin";
    }
}