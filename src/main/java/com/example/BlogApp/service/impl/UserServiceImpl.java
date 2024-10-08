package com.example.BlogApp.service.impl;

import com.example.BlogApp.model.User;
import com.example.BlogApp.repository.UserRepository;
import com.example.BlogApp.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    @Transactional
    public User saveUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        try {
            return userRepository.save(user);
        }catch(DataIntegrityViolationException e){
            throw new UsernameAlreadyExistsException("username already exists");
        }
    }


    @Override
    @Transactional
    public User updateUser(User user) {
        if(userRepository.existsById(user.getId())){
            return userRepository.save(user);
        }
        return null;
    }

    @Override
    public List<User> retrieveUsers() {
        return (userRepository.findAll()!=null) ? userRepository.findAll():null;
    }

}
