package com.geetanjali.bookstore.service;

import com.geetanjali.bookstore.entity.CartItem;
import com.geetanjali.bookstore.entity.Order;

import java.util.List;

public interface OrderService {

    Order createOrder(List<CartItem> cartItems);

    List<Order> getAllOrders();

    Order getOrderById(Long id);
}