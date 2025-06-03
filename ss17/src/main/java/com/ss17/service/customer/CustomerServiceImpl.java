package com.ss17.service.customer;

import com.ss17.entity.Customer;
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
}
