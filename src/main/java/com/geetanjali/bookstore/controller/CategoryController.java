package com.geetanjali.bookstore.controller;

import com.geetanjali.bookstore.entity.Category;
import com.geetanjali.bookstore.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public String getCategories(Model model) {

        model.addAttribute(
                "categories",
                categoryService.getAllCategories()
        );

        return "category-list";
    }

    @GetMapping("/add")
    public String showAddCategoryForm(Model model) {

        model.addAttribute("category", new Category());

        return "add-category";
    }

    @PostMapping("/save")
    public String saveCategory(
            @ModelAttribute Category category) {

        categoryService.saveCategory(category);

        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(
            @PathVariable Long id) {

        categoryService.deleteCategory(id);

        return "redirect:/categories";
    }
}