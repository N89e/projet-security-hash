package com.example.porjet_security.controlers;

import com.example.porjet_security.items.Article;
import com.example.porjet_security.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/article-liste")
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;



    @GetMapping()
    public String articleListe(Model model) {
        List<Article> articles = articleRepository.findAll();
        System.out.println("Nombre d'articles trouvés : " + articles.size());
        model.addAttribute("articles", articles);
        return "article-liste";
    }

}
