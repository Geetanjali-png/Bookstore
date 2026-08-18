package com.geetanjali.bookstore.controller;

import com.geetanjali.bookstore.entity.Order;
import com.geetanjali.bookstore.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String orderHistory(Model model) {

        model.addAttribute(
                "orders",
                orderService.getAllOrders()
        );

        return "order-history";
    }

    @GetMapping("/{id}")
    public String orderConfirmation(
            @PathVariable Long id,
            Model model) {

        Order order = orderService.getOrderById(id);

        model.addAttribute("order", order);

        return "order-confirmation";
    }
}