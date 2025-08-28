package com.example.porjet_security.services;

import com.example.porjet_security.items.UserApp;
import com.example.porjet_security.repository.UserRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public CustomUserDetailsService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(String username, String password) {
        userRepository.save(new UserApp(username, passwordEncoder.encode(password)));
    }

    public boolean checkPassword(UserApp user, String rawPassword) {
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }

    // Chargement de l’utilisateur pour Spring Security
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserApp userApp = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + username));

        return User.builder()
                .username(userApp.getUsername())
                .password(userApp.getPassword())
                .authorities(Collections.emptyList())
                .roles("USER")
                .build();
    }
}
