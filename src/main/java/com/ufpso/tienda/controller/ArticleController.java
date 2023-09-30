package com.ufpso.tienda.controller;

import com.ufpso.tienda.model.Article;
import com.ufpso.tienda.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("articles/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id){
            return new ResponseEntity<>(articleService.getArticleById(id), HttpStatus.OK);
    }

    @GetMapping("articles")
    public ResponseEntity<List<Article>> findAll(){
        return  new ResponseEntity<>(articleService.findAllArticles(),HttpStatus.OK);
    }

    @PostMapping("articles")
    public ResponseEntity<Article> create(@RequestBody Article article){
        return new ResponseEntity<>(articleService.createArticle(article),HttpStatus.CREATED);
    }

    @PutMapping("articles/{id}")
    public ResponseEntity<Article> update(@RequestBody Article articleReq, @PathVariable Long id){
        return new ResponseEntity<>(articleService.updateArticle(articleReq,id),HttpStatus.OK);
    }

    @DeleteMapping("articles/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity<>(Boolean.toString(articleService.deleteArticle(id)),HttpStatus.NO_CONTENT);
    }

}
