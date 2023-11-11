package com.ufpso.tienda.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "ARTICLES")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArticle;

    @NotNull(message = "Name article is required")
    @Size(max = 40, message = "Name article max 40 character")
    private String nameArticle;

    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock cannot be less than 0")
    private Integer stock;

    @NotNull(message = "Description article is required")
    @Size(max = 200, message = "Description article max 200 character")
    private String descriptionArticle;

    @NotNull(message = "Price article is required")
    @Min(value = 0, message = "Price article cannot be less than 0")
    private Double priceArticle;

    @ManyToOne
    @JoinColumn(name = "idCategory", referencedColumnName = "idCategory")
    private Category category;
}
