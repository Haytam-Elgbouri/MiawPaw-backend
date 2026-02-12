package com.yousra.miawpaw.security.configs;

import com.yousra.miawpaw.security.exceptions.CustomAccessDeniedHandler;
import com.yousra.miawpaw.security.exceptions.CustomAuthenticationEntryPoint;
import com.yousra.miawpaw.security.filters.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(request -> {
                    var corsConfig = new org.springframework.web.cors.CorsConfiguration();
                    corsConfig.setAllowCredentials(true);
                    corsConfig.addAllowedOrigin("http://localhost:8080");
                    corsConfig.addAllowedHeader("*");
                    corsConfig.addAllowedMethod("*");
                    return corsConfig;
                }))
                .authorizeHttpRequests(auth -> auth

                    // Auth & public endpoints
                    .requestMatchers("/api/auth/**").permitAll()
                    .requestMatchers("/error").permitAll()
//                    .requestMatchers("/api/public/**").permitAll()
                    .requestMatchers("/api/booked-slots/**").permitAll()

                    // Appointment controller
                    .requestMatchers(HttpMethod.POST, "/api/appointment/online").permitAll()
                    .requestMatchers("/api/appointment/**").hasRole("ADMIN")

                    // Accessory - GET public
                    .requestMatchers(HttpMethod.GET, "/api/accessory/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/accessory/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/accessory/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/accessory/**").hasRole("ADMIN")

                    // Pet - GET public
                    .requestMatchers(HttpMethod.GET, "/api/pet/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/pet/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/pet/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/pet/**").hasRole("ADMIN")

                    // Service - GET public
                    .requestMatchers(HttpMethod.GET, "/api/service/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/api/service/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.PUT, "/api/service/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.DELETE, "/api/service/**").hasRole("ADMIN")

                    // Everything else must be authenticated
                    .anyRequest().authenticated()
                )

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(customAccessDeniedHandler())
                        .authenticationEntryPoint(customAuthenticationEntryPoint())
                );

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    private AuthenticationEntryPoint customAuthenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }

    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
