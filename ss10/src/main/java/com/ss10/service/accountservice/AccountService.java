package com.ss10.service.accountservice;

public interface AccountService{
    boolean register(String username, String password, String email);
    boolean login(String username, String password);
}
