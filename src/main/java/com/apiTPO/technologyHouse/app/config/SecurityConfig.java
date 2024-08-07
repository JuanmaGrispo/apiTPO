package com.apiTPO.technologyHouse.app.config;

import com.apiTPO.technologyHouse.app.models.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

        private final JwtAuthenticationFilter jwtAuthFilter;
        private final AuthenticationProvider authenticationProvider;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(req -> req.requestMatchers("/api/v1/auth/**").permitAll()
                                                .requestMatchers("/error/**").permitAll()
                                                .requestMatchers("/api/products/getByCategory/").permitAll()
                                                .requestMatchers("/api/categories/**").permitAll()
                                                .requestMatchers("/api/products/create").hasAnyAuthority(Role.ADMIN.name())
                                                .requestMatchers("/api/products/sortedBy/{strategy}").permitAll()
                                                .requestMatchers("/api/users/**").hasAnyAuthority(Role.ADMIN.name())
                                                .requestMatchers("/api/shoppingCart/getAll").hasAnyAuthority(Role.ADMIN.name())
                                                .requestMatchers("/api/shoppingCart/addProduct").hasAnyAuthority(Role.CUSTOMER.name())
                                                .requestMatchers("/api/shoppingCart/removeProduct").hasAnyAuthority(Role.CUSTOMER.name())
                                                .anyRequest()
                                                .authenticated())
                                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                                .authenticationProvider(authenticationProvider)
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

                return http.build();
        }
}
