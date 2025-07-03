package com.edutech.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(crsf -> crsf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/students/**", "/classrooms/**").hasRole("OPERATOR")
                        .requestMatchers(HttpMethod.GET, "/students/**").hasAnyRole("TEACHER", "COORDINATOR")
                        .requestMatchers(HttpMethod.GET, "/classrooms/**").hasRole("COORDINATOR")
                        .requestMatchers("*/**").hasRole("ADMIN")
                )
                .build();
    }

}
