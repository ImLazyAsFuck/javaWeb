package com.ss17.service.customer;

import com.ss17.entity.Customer;
import com.ss17.model.CustomerRole;
import com.ss17.repo.customer.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public List<Customer> findAll(){
        return customerRepo.findAll();
    }

    @Override
    public List<Customer> findAll(int page, int size){
        return  customerRepo.findAll(page, size);
    }

    @Override
    public Customer findById(int id){
        return customerRepo.findById(id);
    }

    @Override
    public void save(Customer customer){
        customerRepo.save(customer);
    }

    @Override
    public void delete(Customer customer){
        customerRepo.delete(customer);
    }

    @Override
    public Customer login(String username, String password){
        return customerRepo.login(username, password);
    }

    @Override
    public boolean isUsernameExist(String username){
        return customerRepo.isUsernameExist(username);
    }

    @Override
    public List<Customer> findByRole(CustomerRole role, int page, int size){
        return  customerRepo.findByRole(role, page, size);
    }

    @Override
    public List<Customer> findByName(String name, int page, int size){
        return customerRepo.findByName(name, page, size);
    }

    @Override
    public List<Customer> findByNameAndRole(String name, CustomerRole role, int page, int size){
        return customerRepo.findByNameAndRole(name, role, page, size);
    }

    @Override
    public long countCustomers(){
        return customerRepo.countCustomers();
    }

    @Override
    public long countCByName(String name){
        return customerRepo.countCByName(name);
    }

    @Override
    public long countCByRole(CustomerRole role){
        return customerRepo.countCByRole(role);
    }

    @Override
    public long countCByNameAndRole(String name, CustomerRole role){
        return customerRepo.countCByNameAndRole(name, role);
    }

    @Override
    public void update(Customer customer){
        customerRepo.update(customer);
    }
}
