package com.example.porjet_security.config;

import com.example.porjet_security.items.Article;
import com.example.porjet_security.items.UserApp;
import com.example.porjet_security.repository.ArticleRepository;
import com.example.porjet_security.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ArticleRepository articleRepository;

    public DataInitializer(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           ArticleRepository articleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.articleRepository = articleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new UserApp("user1", passwordEncoder.encode("password1")));
        userRepository.save(new UserApp("admin", passwordEncoder.encode("adminpass")));
        articleRepository.save(new Article("TOTO", "Raconte une histoire", "user1"));
        System.out.println("Données initiales insérées dans user_app");
        System.out.println("Données initiales insérées dans article");

    }

}
