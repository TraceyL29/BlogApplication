package com.example.BlogApp.config;

import com.example.BlogApp.repository.UserRepository;
import com.example.BlogApp.service.impl.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.UserDetailsService;


@Configuration
@RequiredArgsConstructor
public class SecurityConfig  {

    private final CustomUserDetailsService customUserDetailsService;



    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception{
        httpSecurity.csrf(csrf -> csrf.disable()).authorizeHttpRequests(authorizeHttpRequest ->
                // Allow access to these endpoints without authentication
                authorizeHttpRequest.requestMatchers("/request", "/login").permitAll().
                        anyRequest().authenticated()
                ).formLogin(formLogin ->
                formLogin.loginPage("/login").permitAll().loginPage("login").permitAll()
                ).logout(logout ->  logout.permitAll()
                );

        return httpSecurity.build();

    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManagerBuilder authenticationManager() throws Exception{
        AuthenticationManagerBuilder authenticationManagerBuilder = new AuthenticationManagerBuilder(null);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder;
    }


}
