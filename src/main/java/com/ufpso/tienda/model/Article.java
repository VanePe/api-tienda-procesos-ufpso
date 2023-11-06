package com.ufpso.tienda.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ARTICLES")
public class Article {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticle;

    private String nameArticle;
    private Integer stock;
    private String descriptionArticle;
    private Double priceArticle;

    @ManyToOne
    @JoinColumn(name = "idCategory", referencedColumnName = "idCategory")
    private Category category;
}
