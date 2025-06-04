package com.ss17.service.order;

import com.ss17.entity.Order;
import com.ss17.model.OrderStatus;
import com.ss17.repo.order.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;

    @Override
    public List<Order> findAll(){
        return orderRepo.findAll();
    }

    @Override
    public Order findById(int id){
        return orderRepo.findById(id);
    }

    @Override
    public List<Order> findByCustomerId(int id){
        return orderRepo.findByCustomerId(id);
    }

    @Override
    public void save(Order order){
        orderRepo.save(order);
    }

    @Override
    public void update(Order order){
        orderRepo.save(order);
    }

    @Override
    public void delete(Order order){
        orderRepo.delete(order);
    }

    @Override
    public List<Order> findByCustomerId(int id, int page, int size){
        return orderRepo.findByCustomerId(id,page, size);
    }

    @Override
    public int countByCustomerId(int customerId){
        return orderRepo.countByCustomerId(customerId);
    }

    @Override
    public List<Order> searchOrders(String receiverName, Date orderDate, OrderStatus status, int page, int size){
        return orderRepo.searchOrders(receiverName, orderDate, status, page, size);
    }

    @Override
    public int countSearchOrders(String receiverName, Date orderDate, OrderStatus status){
        return orderRepo.countSearchOrders(receiverName, orderDate, status);
    }

}
