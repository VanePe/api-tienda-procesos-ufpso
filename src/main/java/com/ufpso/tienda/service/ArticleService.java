package com.ufpso.tienda.service;

import com.ufpso.tienda.model.Article;
import com.ufpso.tienda.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Article createArticle(Article article){
        return articleRepository.save(article);
    }

    public Article getArticleById(Long id){
        return articleRepository.findById(id).get();
    }

    public List<Article> findAllArticles(){
        return (List<Article>) articleRepository.findAll();
    }


    public boolean deleteArticle(Long id){
        Optional<Article> articleBd = articleRepository.findById(id);
        if(articleBd.isEmpty())
            return false;
        articleRepository.deleteById(articleBd.get().getIdArticle());
        return true;
    }

}
