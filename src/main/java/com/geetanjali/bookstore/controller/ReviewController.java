package com.geetanjali.bookstore.controller;

import com.geetanjali.bookstore.entity.Book;
import com.geetanjali.bookstore.entity.Review;
import com.geetanjali.bookstore.repository.BookRepository;
import com.geetanjali.bookstore.repository.ReviewRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;

    public ReviewController(
            ReviewRepository reviewRepository,
            BookRepository bookRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
    }

    @PostMapping("/add/{bookId}")
    public String addReview(
            @PathVariable Long bookId,
            @RequestParam String reviewerName,
            @RequestParam int rating,
            @RequestParam String comment) {

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() ->
                        new RuntimeException("Book not found"));

        Review review = new Review();

        review.setReviewerName(reviewerName);
        review.setRating(rating);
        review.setComment(comment);
        review.setReviewDate(LocalDateTime.now());
        review.setBook(book);

        reviewRepository.save(review);

        return "redirect:/books/" + bookId;
    }
}