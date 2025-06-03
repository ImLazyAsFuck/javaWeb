package com.ss17.service.customer;

import com.ss17.entity.Customer;

import java.util.List;

public interface CustomerService{
    List<Customer> findAll();
    Customer findById(int id);
    void save(Customer customer);
    void delete(Customer customer);
    Customer login(String username, String password);
    boolean isUsernameExist(String username);
}
