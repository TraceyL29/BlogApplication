package com.example.BlogApp.controller;

import com.example.BlogApp.model.LoginResponse;
import com.example.BlogApp.model.User;
import com.example.BlogApp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/login")
    public String login() {
        return "login"; // This will look for login.html in the templates folder (for Thymeleaf) or static folder (for plain HTML)
    }

    @PostMapping("/perform_login")
    public ResponseEntity<String> performLogin(   @RequestParam String username,
                                                  @RequestParam String password) {
        // Authentication logic here
        return ResponseEntity.ok("Login successful");
    }


    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){
        User regis = userService.saveUser(user);
        if (regis!=null) {
            // In a real application, you would generate a token or session here
            return ResponseEntity.ok("Register successful!");
        } else {
            return ResponseEntity.status(401).body("Register unsuccessful!");
        }
    }

    @GetMapping("/retrieve")
    public  List<User> retrieveAll(){
        return userService.retrieveUsers();
    }


}
