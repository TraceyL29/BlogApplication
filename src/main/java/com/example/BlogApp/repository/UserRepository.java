package com.example.BlogApp.repository;

import com.example.BlogApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>
{
//    Optional<User> findByUsername(String userName);
}
