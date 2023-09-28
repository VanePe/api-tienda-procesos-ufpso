package com.ufpso.tienda.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "CATEGORIES")
public class Category{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCategory;
    private String nameCategory;

}