package com.geetanjali.bookstore.controller;

import com.geetanjali.bookstore.entity.User;
import com.geetanjali.bookstore.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(
            UserService userService,
            PasswordEncoder passwordEncoder) {

        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegisterPage(Model model) {

        model.addAttribute("user", new User());

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(
            @ModelAttribute("user") User user,
            Model model) {

        if (userService.existsByEmail(user.getEmail())) {

            model.addAttribute(
                    "error",
                    "Email already registered."
            );

            return "register";
        }

        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        userService.saveUser(user);

        return "redirect:/login?registered=true";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }
}