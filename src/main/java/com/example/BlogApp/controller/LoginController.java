package com.example.BlogApp.controller;

import com.example.BlogApp.model.User;
import com.example.BlogApp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        Boolean authenticatedUser = userService.authenticateUser(username, password);
        if (authenticatedUser) {
            // In a real application, you would generate a token or session here
            return ResponseEntity.ok("Login successful!");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
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
    public  void retrieveAll(Model model){
        List<User> allUsers = userService.retrieveUsers();
            model.addAttribute("allUsers", allUsers);
            // In a real application, you would generate a token or session here
    }



}
