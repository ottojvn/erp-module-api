package com.ottojvn.erp_module_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public UserDetailsService userDetailsService() {
       InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
       manager
           .createUser(User.withUsername("admin")
           .password("{noop}admin")
           .roles("ADMIN")
           .build());
        return manager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").hasRole("ADMIN")
                .anyRequest().authenticated())
            .httpBasic(org.springframework.security.config.Customizer.withDefaults())
            .build();
    }

}
