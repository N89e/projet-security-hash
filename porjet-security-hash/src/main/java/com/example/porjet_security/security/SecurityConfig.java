package com.example.porjet_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Désactivation CSRF pour les APIs (à adapter selon besoins)
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")
                        .disable()
                )
                // Autoriser accès aux ressources H2 console dans iframe
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                // Configurer les règles d'accès
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/hello/public",
                                "/get-cookie",
                                "/create-jwt",
                                "/get-jwt",
                                "/verify-jwt/{jwt}",
                                "/user-login",
                                "/register",
                                "/h2-console/**"
                        )
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                // Utiliser session stateless (pas de session, adapté aux API REST JWT)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // Désactiver formLogin pour API REST, éviter redirection vers /login
                .formLogin(form -> form.disable())
                // Activer HTTP Basic si besoin (optionnel)
                .httpBasic(httpBasic -> httpBasic.disable());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
