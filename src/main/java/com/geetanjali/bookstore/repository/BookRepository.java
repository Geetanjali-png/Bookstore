package com.geetanjali.bookstore.repository;

import com.geetanjali.bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByAuthorNameContainingIgnoreCase(String name);

    List<Book> findByCategoryNameContainingIgnoreCase(String name);
}