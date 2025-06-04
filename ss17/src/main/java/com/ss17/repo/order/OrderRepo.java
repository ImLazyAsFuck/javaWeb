package com.ss17.repo.order;

import com.ss17.entity.Order;
import com.ss17.model.CustomerRole;
import com.ss17.model.OrderStatus;
import java.util.Date;

import java.util.List;

public interface OrderRepo{
    List<Order> findAll();
    List<Order> findAll(int page, int size);
    Order findById(int id);
    List<Order> findByCustomerId(int id);
    List<Order> findByCustomerId(int id, int page, int size);
    int countByCustomerId(int customerId);
    void save(Order order);
    void update(Order order);
    void delete (Order order);
    List<Order> searchOrders(String receiverName, Date orderDate, OrderStatus status, int page, int size);
    int countSearchOrders(String receiverName, Date orderDate, OrderStatus status);
}
