package com.example.BlogApp.service.impl;

import com.example.BlogApp.model.User;
import com.example.BlogApp.repository.UserRepository;
import com.example.BlogApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.PasswordAuthentication;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }


    @Override
    public User updateUser(User user) {
        if(userRepository.existsById(user.getId())){
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public List<User> retrieveUsers() {
        return (userRepository.findAll()!=null) ? userRepository.findAll():null ;
    }

    @Override
    public boolean authenticateUser(String userName, String password) {
            User user = userRepository.findByUserName(userName).orElse(null);
            if(user!=null&& passwordEncoder.matches(password, user.getUserName()))
                return true;
            return false;
    }


}
