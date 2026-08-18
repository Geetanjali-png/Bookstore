package com.geetanjali.bookstore.service.impl;

import com.geetanjali.bookstore.entity.CartItem;
import com.geetanjali.bookstore.entity.Order;
import com.geetanjali.bookstore.entity.OrderItem;
import com.geetanjali.bookstore.repository.OrderRepository;
import com.geetanjali.bookstore.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(List<CartItem> cartItems) {

        if (cartItems == null || cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();

        order.setOrderDate(LocalDateTime.now());
        order.setStatus("PLACED");

        double total = 0;

        for (CartItem cartItem : cartItems) {

            OrderItem orderItem = new OrderItem();

            orderItem.setBookId(cartItem.getBookId());
            orderItem.setBookTitle(cartItem.getTitle());
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setQuantity(cartItem.getQuantity());

            orderItem.setOrder(order);

            order.getItems().add(orderItem);

            total += cartItem.getTotal();
        }

        order.setTotalAmount(total);

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {

        return orderRepository.findAllByOrderByOrderDateDesc();
    }

    @Override
    public Order getOrderById(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));
    }
}