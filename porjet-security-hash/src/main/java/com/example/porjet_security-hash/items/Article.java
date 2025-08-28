package com.example.porjet_security.items;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String titre;

    private String contenu;


    private String username;

    public Article(String titre, String contenu, String username) {
        this.titre=titre;
        this.contenu=contenu;
        this.username=username;
    }

}
