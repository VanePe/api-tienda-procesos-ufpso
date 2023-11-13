package com.ufpso.tienda.service;

import com.ufpso.tienda.exception.NotFoundException;
import com.ufpso.tienda.model.Article;
import com.ufpso.tienda.model.Category;
import com.ufpso.tienda.repository.ArticleRepository;
import com.ufpso.tienda.util.ExepctionsConstans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CategoryService categoryService;

    public Article getArticleById(Long id){ //Bucar por ID
        if(id == null)
            throw new NotFoundException(ExepctionsConstans.ARTICLE_IS_NULL.getMessage());
        Optional<Article> articleBd = articleRepository.findById(id);
        if(articleBd.isEmpty())
            throw new NotFoundException(ExepctionsConstans.ARTICLE_NOT_FOUND.getMessage());
        return articleBd.get();
    }

    public List<Article> findAllArticles(){ //Listar todos los articulos
        return (List<Article>) articleRepository.findAll();
    }

    public Article createArticle(Article article, Long idCategory) { //Crear un articulo
        Category categoryBd = categoryService.getCategoryById(idCategory);
        article.setCategory(categoryBd);
        return articleRepository.save(article);
    }

    public Article updateArticle(Article articleReq,Long id){
        if( id == null )
            throw new NotFoundException(ExepctionsConstans.ARTICLE_IS_NULL.getMessage());
        Optional<Article> articleBd = articleRepository.findById(id);
        if(articleBd.isEmpty())
            throw new RuntimeException(ExepctionsConstans.ARTICLE_NOT_FOUND.getMessage());
        articleBd.get().setNameArticle(articleReq.getNameArticle());
        articleBd.get().setDescriptionArticle(articleReq.getDescriptionArticle());
        articleBd.get().setStock(articleReq.getStock());
        articleBd.get().setPriceArticle(articleReq.getPriceArticle());

        Category categoryBd = categoryService.getCategoryById(articleReq.getCategory().getIdCategory());
        articleReq.setCategory(categoryBd);
        articleBd.get().setCategory(articleReq.getCategory());
        return articleRepository.save(articleBd.get());
    }

    public boolean deleteArticle(Long id){
        Article article = this.getArticleById(id);
        articleRepository.delete(article);
        return true;
    }

}
