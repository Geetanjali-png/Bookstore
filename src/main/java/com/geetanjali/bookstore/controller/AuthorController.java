package com.geetanjali.bookstore.controller;

import com.geetanjali.bookstore.entity.Author;
import com.geetanjali.bookstore.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String getAuthors(Model model) {

        model.addAttribute(
                "authors",
                authorService.getAllAuthors()
        );

        return "author-list";
    }

    @GetMapping("/add")
    public String showAddAuthorForm(Model model) {

        model.addAttribute("author", new Author());

        return "add-author";
    }

    @PostMapping("/save")
    public String saveAuthor(
            @ModelAttribute Author author) {

        authorService.saveAuthor(author);

        return "redirect:/authors";
    }

    @GetMapping("/delete/{id}")
    public String deleteAuthor(
            @PathVariable Long id) {

        authorService.deleteAuthor(id);

        return "redirect:/authors";
    }
}