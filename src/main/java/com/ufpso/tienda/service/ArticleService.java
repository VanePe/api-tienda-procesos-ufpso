package com.ufpso.tienda.service;

import com.ufpso.tienda.exception.NotFoundException;
import com.ufpso.tienda.model.Article;
import com.ufpso.tienda.model.Category;
import com.ufpso.tienda.repository.ArticleRepository;
import com.ufpso.tienda.util.ExepctionsConstans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CategoryService categoryService;

    public Article getArticleById(Long id){ //Bucar por ID
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
        Optional<Article> articleBd = articleRepository.findById(id);
        if(articleBd.isEmpty())
            throw new RuntimeException(ExepctionsConstans.ARTICLE_NOT_FOUND.getMessage());
        articleBd.get().setNameArticle(articleReq.getNameArticle());
        articleBd.get().setDescriptionArticle(articleReq.getDescriptionArticle());
        articleBd.get().setStock(articleReq.getStock());
        articleBd.get().setPriceArticle(articleReq.getPriceArticle());
        articleBd.get().setCategory(articleReq.getCategory());
        return articleRepository.save(articleBd.get());
    }

    public boolean deleteArticle(Long id){
        Optional<Article> articleBd = articleRepository.findById(id);
        if(articleBd.isEmpty())
            throw new RuntimeException(ExepctionsConstans.ARTICLE_NOT_FOUND.getMessage());
        articleRepository.deleteById(articleBd.get().getIdArticle());
        return true;
    }

}
