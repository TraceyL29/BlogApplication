package com.example.BlogApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Table (name = "users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotNull
    @Size(min = 4, max = 20)
    @Column(name = "user_name", unique = true, columnDefinition = "TEXT")
    private String userName;
    @Column(name = "password", columnDefinition = "VARCHAR(255)")
    private String password;
     @Column(name = "email", columnDefinition = "TEXT")
    private String email;


}
