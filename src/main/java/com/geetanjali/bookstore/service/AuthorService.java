package com.geetanjali.bookstore.service;

import com.geetanjali.bookstore.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Author saveAuthor(Author author);

    List<Author> getAllAuthors();

    Optional<Author> getAuthorById(Long id);

    void deleteAuthor(Long id);
}