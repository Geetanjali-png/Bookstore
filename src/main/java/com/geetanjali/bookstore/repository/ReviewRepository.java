package com.geetanjali.bookstore.repository;

import com.geetanjali.bookstore.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByBookIdOrderByReviewDateDesc(Long bookId);
}