package com.example.porjet_security.repository;

import com.example.porjet_security.items.Article;
import com.example.porjet_security.items.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByTitre(String titre);
}
