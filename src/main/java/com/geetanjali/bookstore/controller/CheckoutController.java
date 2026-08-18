package com.geetanjali.bookstore.controller;

import com.geetanjali.bookstore.entity.CartItem;
import com.geetanjali.bookstore.entity.Order;
import com.geetanjali.bookstore.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {

    private final OrderService orderService;

    public CheckoutController(OrderService orderService) {
        this.orderService = orderService;
    }

    @SuppressWarnings("unchecked")
    private List<CartItem> getCart(HttpSession session) {

        return (List<CartItem>) session.getAttribute("cart");
    }

    @GetMapping
    public String checkout(HttpSession session, Model model) {

        List<CartItem> cart = getCart(session);

        if (cart == null || cart.isEmpty()) {
            return "redirect:/cart";
        }

        double total = cart.stream()
                .mapToDouble(CartItem::getTotal)
                .sum();

        model.addAttribute("cartItems", cart);
        model.addAttribute("total", total);

        return "checkout";
    }

    @PostMapping("/place")
    public String placeOrder(HttpSession session) {

        List<CartItem> cart = getCart(session);

        if (cart == null || cart.isEmpty()) {
            return "redirect:/cart";
        }

        Order order = orderService.createOrder(cart);

        session.removeAttribute("cart");

        return "redirect:/orders/" + order.getId();
    }
}