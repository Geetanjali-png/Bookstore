package com.geetanjali.bookstore.service.impl;

import com.geetanjali.bookstore.entity.Book;
import com.geetanjali.bookstore.repository.BookRepository;
import com.geetanjali.bookstore.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<Book> searchBooks(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public List<Book> findBooksByAuthor(String authorName) {
        return bookRepository.findByAuthorNameContainingIgnoreCase(authorName);
    }

    @Override
    public List<Book> findBooksByCategory(String categoryName) {
        return bookRepository.findByCategoryNameContainingIgnoreCase(categoryName);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}