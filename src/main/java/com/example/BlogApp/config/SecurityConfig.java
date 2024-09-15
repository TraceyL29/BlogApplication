package com.example.BlogApp.config;

import com.example.BlogApp.repository.UserRepository;
import com.example.BlogApp.service.impl.CustomUserDetailsService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@RequiredArgsConstructor
public class SecurityConfig  {

    private final CustomUserDetailsService customUserDetailsService;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/register", "/login.html", "/perform_login", "/retrieve", "/retrieve.html").permitAll()
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login.html") // Note: This should be handled by React
                                .loginProcessingUrl("/perform_login")
                                .successHandler((request, response, authentication) -> {
                                    // Send a 200 OK response on successful login
                                    response.setStatus(HttpServletResponse.SC_OK);
                                })
                                .failureHandler((request, response, exception) -> {
                                    // Send a 401 Unauthorized response if login fails
                                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                })
                                .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return httpSecurity.build();
    }



    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }




}
