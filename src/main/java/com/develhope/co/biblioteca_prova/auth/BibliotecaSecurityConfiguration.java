package com.develhope.co.biblioteca_prova.auth;

import com.develhope.co.biblioteca_prova.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class BibliotecaSecurityConfiguration {
    @Bean
    public PasswordEncoder pwEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Profile({"prod", "test"})
    public SecurityFilterChain filterChainProd(HttpSecurity http, CustomUserDetailsService userDetailsService)
            throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/libri/**").authenticated()
                        .requestMatchers("/admin/**").hasAnyRole("OPERATORE")
                        .requestMatchers("/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());

        http.userDetailsService(userDetailsService);

        return http.build();
    }

    @Bean
    @Profile({"dev","personal"})
    public SecurityFilterChain filterChainDev(HttpSecurity http, CustomUserDetailsService userDetailsService)
            throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                );
        http.userDetailsService(userDetailsService);

        return http.build();
    }
}
