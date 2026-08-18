package com.geetanjali.bookstore.controller;

import com.geetanjali.bookstore.entity.Book;
import com.geetanjali.bookstore.entity.CartItem;
import com.geetanjali.bookstore.repository.BookRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final BookRepository bookRepository;

    public CartController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @SuppressWarnings("unchecked")
    private List<CartItem> getCart(HttpSession session) {

        List<CartItem> cart =
                (List<CartItem>) session.getAttribute("cart");

        if (cart == null) {
            cart = new ArrayList<>();
            session.setAttribute("cart", cart);
        }

        return cart;
    }

    @GetMapping
    public String cart(HttpSession session, Model model) {

        List<CartItem> cart = getCart(session);

        double total = cart.stream()
                .mapToDouble(CartItem::getTotal)
                .sum();

        model.addAttribute("cartItems", cart);
        model.addAttribute("cartTotal", total);

        return "cart";
    }

    @GetMapping("/add/{id}")
    public String addToCart(@PathVariable Long id,
                            HttpSession session) {

        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        List<CartItem> cart = getCart(session);

        for (CartItem item : cart) {

            if (item.getBookId().equals(book.getId())) {

                item.setQuantity(item.getQuantity() + 1);

                return "redirect:/cart";
            }
        }

        CartItem item = new CartItem(
                book.getId(),
                book.getTitle(),
                book.getPrice(),
                1
        );

        cart.add(item);

        return "redirect:/cart";
    }

    @GetMapping("/increase/{id}")
    public String increase(@PathVariable Long id,
                           HttpSession session) {

        List<CartItem> cart = getCart(session);

        for (CartItem item : cart) {

            if (item.getBookId().equals(id)) {
                item.setQuantity(item.getQuantity() + 1);
                break;
            }
        }

        return "redirect:/cart";
    }

    @GetMapping("/decrease/{id}")
    public String decrease(@PathVariable Long id,
                           HttpSession session) {

        List<CartItem> cart = getCart(session);

        cart.removeIf(item -> {
            if (item.getBookId().equals(id)) {

                if (item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1);
                    return false;
                }

                return true;
            }

            return false;
        });

        return "redirect:/cart";
    }

    @GetMapping("/remove/{id}")
    public String remove(@PathVariable Long id,
                         HttpSession session) {

        List<CartItem> cart = getCart(session);

        cart.removeIf(item ->
                item.getBookId().equals(id));

        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clear(HttpSession session) {

        session.removeAttribute("cart");

        return "redirect:/cart";
    }
}