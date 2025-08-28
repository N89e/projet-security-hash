package com.example.porjet_security.controlers;

import com.example.porjet_security.items.Article;
import com.example.porjet_security.items.UserApp;
import com.example.porjet_security.repository.ArticleRepository;
import com.example.porjet_security.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PageController {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/register")
    public String createUserPage(Model model) {
        model.addAttribute("userApp", new UserApp());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserApp userApp) throws Exception {
                customUserDetailsService.createUser(
                userApp.getUsername(),
                userApp.getPassword()
        );
        return "redirect:/login?registered";
    }

    @GetMapping("/add-article")
    public String addArticle(Model model) {
        model.addAttribute("article", new Article());
        return "add-article";
    }

    @PostMapping("/add-article")
    public String registerArticle(@ModelAttribute Article article, Authentication authentication) throws Exception {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        Article newArticle = new Article(article.getTitre(), article.getContenu(), username);
        articleRepository.save(newArticle);
        return "redirect:/article-liste";
    }

}
