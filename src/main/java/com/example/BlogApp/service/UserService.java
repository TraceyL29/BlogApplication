package com.example.BlogApp.service;

import com.example.BlogApp.model.User;

import java.util.List;

public interface UserService  {
    User saveUser(User user);
    User updateUser(User user);
    List<User> retrieveUsers();
}
