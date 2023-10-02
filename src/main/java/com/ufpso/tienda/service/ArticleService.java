package com.ufpso.tienda.service;

import com.ufpso.tienda.model.Article;
import com.ufpso.tienda.model.Category;
import com.ufpso.tienda.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Article getArticleById(Long id){
        Optional<Article> articleBd = articleRepository.findById(id);
        return articleBd.isEmpty() ? null : articleBd.get();
    }

    public List<Article> findAllArticles(){
        return (List<Article>) articleRepository.findAll();
    }

    public Article createArticle(Article article){
        return articleRepository.save(article);
    }

    public Article updateArticle(Article articleReq,Long id){
        Optional<Article> articleBd = articleRepository.findById(id);
        if(articleBd.isEmpty())
            return null;
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
            return false;
        articleRepository.deleteById(articleBd.get().getIdArticle());
        return true;
    }

}
