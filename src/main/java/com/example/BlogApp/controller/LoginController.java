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

@Controller
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("userName", "a");
        model.addAttribute("password", "password");
        return "login"; // This will look for login.html in the templates folder (for Thymeleaf) or static folder (for plain HTML)
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User user) {
        Boolean authenticatedUser = userService.authenticateUser(user.getUserName(), user.getPassword());
        if (authenticatedUser) {
            // Create a response object with status and message
            LoginResponse response = new LoginResponse("Login successful!", HttpStatus.OK.value());
            return ResponseEntity.ok(response);
        } else {
            LoginResponse response = new LoginResponse("Login unsuccessful!", HttpStatus.UNAUTHORIZED.value());
            // Create a response object with status and message
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
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
    public  List<User> retrieveAll(){
        return userService.retrieveUsers();
    }


}
