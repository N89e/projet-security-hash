//package com.example.porjet_security.services;
//
//import com.example.porjet_security.items.Article;
//import com.example.porjet_security.repository.ArticleRepository;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Collections;
//
//@Service
//public class CustomArticleDetailsService implements ArticleDetailsService {
//
//    private final ArticleRepository articleRepository;
//
//    public CustomArticleDetailsService(ArticleRepository articleRepository) {
//        this.articleRepository = articleRepository;
//    }
//
//    public void createArticle(String titre, String contenu) {
//        articleRepository.save(new Article(titre, contenu));
//    }
//
//    // Chargement de l’utilisateur pour Spring Security
//    @Override
//    public ArticleDetail loadUserByTitre(String titre) throws UsernameNotFoundException {
//        Article article = articleRepository.findByTitre(titre)
//                .orElseThrow(() -> new UsernameNotFoundException("Titre non trouvé : " + titre));
//
//        return article.withTitre(article.getTitre())
//                .contenu(article.getContenu())
//                .authorities(Collections.emptyList()) // Définir vos rôles ici si besoin
//                .build();
//    }
//}
