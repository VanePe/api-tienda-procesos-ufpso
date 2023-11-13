package com.ufpso.tienda.repository;

import com.ufpso.tienda.model.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article,Long> {
}
