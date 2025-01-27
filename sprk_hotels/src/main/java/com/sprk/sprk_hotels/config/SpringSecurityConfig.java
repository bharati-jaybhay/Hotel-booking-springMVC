package com.sprk.sprk_hotels.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SpringSecurityConfig {



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {

        http.authorizeHttpRequests(configurer -> configurer
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/listings/signup", "/listings/login",
                        "/")
                .permitAll()
                .anyRequest().authenticated());
        http.formLogin(form -> form
                .loginPage("/listings/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/authenticateUser")
                .permitAll());



        http.logout(logout -> logout.permitAll());
        http.csrf(csrf -> csrf.disable());
//        http.exceptionHandling(configurer -> configurer.accessDeniedPage("/imagegallery/accessdenied"));

        return http.build();
    }

}
