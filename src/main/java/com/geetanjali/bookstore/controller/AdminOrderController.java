package com.geetanjali.bookstore.controller;

import com.geetanjali.bookstore.entity.Order;
import com.geetanjali.bookstore.repository.OrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {

    private final OrderRepository orderRepository;

    public AdminOrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public String orders(Model model) {

        model.addAttribute(
                "orders",
                orderRepository.findAllByOrderByOrderDateDesc()
        );

        return "admin-orders";
    }

    @PostMapping("/status/{id}")
    public String updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        Order order = orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        order.setStatus(status);

        orderRepository.save(order);

        return "redirect:/admin/orders";
    }
}