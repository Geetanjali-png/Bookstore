package com.geetanjali.bookstore.service;

import com.geetanjali.bookstore.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    Book saveBook(Book book);

    List<Book> getAllBooks();

    Optional<Book> getBookById(Long id);

    List<Book> searchBooks(String title);

    List<Book> findBooksByAuthor(String authorName);

    List<Book> findBooksByCategory(String categoryName);

    void deleteBook(Long id);
}