package com.ss10.service.accountservice;

import com.ss10.dao.accountdao.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    private AccountDAO accountDAO;

    @Override
    public boolean register(String username, String password, String email){
        return accountDAO.register(username, password, email);
    }

    @Override
    public boolean login(String username, String password){
        return accountDAO.login(username, password);
    }
}
