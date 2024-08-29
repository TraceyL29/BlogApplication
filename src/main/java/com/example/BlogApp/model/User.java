package com.example.BlogApp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table (name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_name", columnDefinition = "TEXT")
    private String userName;
    @Column(name = "password", columnDefinition = "VARCHAR(255)")
    private String password;
     @Column(name = "email", columnDefinition = "TEXT")
    private String email;



}
