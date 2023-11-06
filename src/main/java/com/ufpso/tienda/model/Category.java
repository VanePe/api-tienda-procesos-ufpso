package com.ufpso.tienda.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "CATEGORIES")
public class Category{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;
    private String nameCategory;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Article> articleList;

}