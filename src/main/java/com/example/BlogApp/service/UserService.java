package com.example.BlogApp.service;

import com.example.BlogApp.model.User;

public interface UserService  {
    User saveUser(User user);
    User updateUser(User user);
    boolean authenticateUser(String userName, String password);
}
