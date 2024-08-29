package com.example.BlogApp.service.impl;

import com.example.BlogApp.repository.UserRepository;
import com.example.BlogApp.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;



    @Override
    public int saveUser() {
        return 1;
    }

    @Override
    public int updateUser() {
        return 1;
    }

    @Override
    public boolean authenticateUser() {
        return true;
    }
}
