package com.ss10.dao.accountdao;

public interface AccountDAO{
    boolean register(String username, String password, String email);
    boolean login(String username, String password);
}
