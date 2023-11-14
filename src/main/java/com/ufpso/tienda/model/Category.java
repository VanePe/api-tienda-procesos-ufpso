package com.ufpso.tienda.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@Table(
        name = "CATEGORIES"
)
public class Category{

    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;

    @NotNull(message = "Name category es required")
    @Size(max = 40,message = "Name category max 40 character")
    private String nameCategory;

    @NotNull(message = "Description category is required")
    @Size(max = 225,message = "Description category max 225 characters")
    private String descriptionCategory;

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Article> articleList;

}
