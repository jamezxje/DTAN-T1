package com.capstone.app.security;

import com.capstone.app.util.RequestURI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurity {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        requests -> requests
                        .requestMatchers(RequestURI.PUBLIC_UNAUTHENTICATION_URI).permitAll()
                        .requestMatchers("/dashboard/**").hasAnyRole("OWNER", "ADMIN")
                        .requestMatchers("/dashboard/posts/**").hasAnyRole( "ADMIN")
                        .requestMatchers("/details/*/booking/**", "/booking/**").hasAnyRole("CUSTOMER")
                        .anyRequest().authenticated()
                )
                .formLogin( form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/welcome", true)
                        .permitAll()
                )
                .logout( logout -> logout
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                        .permitAll());


        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
