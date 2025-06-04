package com.ss17.service.order;

import com.ss17.entity.Order;
import com.ss17.model.OrderStatus;

import java.util.Date;
import java.util.List;

public interface OrderService{
    List<Order> findAll();
    Order findById(int id);
    List<Order> findByCustomerId(int id);
    void save(Order order);
    void update(Order order);
    void delete (Order order);
    List<Order> findByCustomerId(int id, int page, int size);
    int countByCustomerId(int customerId);
    List<Order> searchOrders(String receiverName, Date orderDate, OrderStatus status, int page, int size);
    int countSearchOrders(String receiverName, Date orderDate, OrderStatus status);
}
