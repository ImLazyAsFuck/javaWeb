package com.ss9.service.loginservice;

import com.ss9.model.Customer;
import com.ss9.repository.loginrepository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImp implements LoginService{
    @Autowired
    private LoginRepository loginRepository;


    @Override
    public Customer login(Customer customer){
        return loginRepository.login(customer);
    }
}
