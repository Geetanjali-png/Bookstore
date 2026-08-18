package com.geetanjali.bookstore.service;

import com.geetanjali.bookstore.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    Category saveCategory(Category category);

    List<Category> getAllCategories();

    Optional<Category> getCategoryById(Long id);

    void deleteCategory(Long id);
}