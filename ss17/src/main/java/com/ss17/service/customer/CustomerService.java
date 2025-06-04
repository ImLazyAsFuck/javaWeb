package com.ss17.service.customer;

import com.ss17.entity.Customer;
import com.ss17.model.CustomerRole;

import java.util.List;

public interface CustomerService{
    List<Customer> findAll();
    List<Customer> findAll(int page, int size);
    Customer findById(int id);
    void save(Customer customer);
    void delete(Customer customer);
    Customer login(String username, String password);
    boolean isUsernameExist(String username);
    List<Customer> findByRole(CustomerRole role, int page, int size);
    List<Customer> findByName(String name, int page, int size);
    List<Customer> findByNameAndRole(String name, CustomerRole role, int page, int size);
    long countCustomers();
    long countCByName(String name);
    long countCByRole(CustomerRole role);
    long countCByNameAndRole(String name, CustomerRole role);
    void update(Customer customer);
}
