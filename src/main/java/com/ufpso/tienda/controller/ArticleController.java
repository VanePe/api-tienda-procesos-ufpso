package com.ufpso.tienda.controller;

import com.ufpso.tienda.model.Article;
import com.ufpso.tienda.service.ArticleService;
import jakarta.validation.Valid;
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
        Article articleBd = articleService.getArticleById(id);
        HttpStatus status = (articleBd != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(articleBd, status);
    }

    @GetMapping("articles")
    public ResponseEntity<List<Article>> findAll(){
        return new ResponseEntity<>(articleService.findAllArticles(),HttpStatus.OK);
    }

    @PostMapping("articles/{idCategory}")
    public ResponseEntity<Article> create(@Valid @RequestBody Article article, @PathVariable Long idCategory){
        return new ResponseEntity<>(articleService.createArticle(article, idCategory),HttpStatus.CREATED);
    }

    @PutMapping("articles/{id}")
    public ResponseEntity<Article> update(@RequestBody Article articleReq, @PathVariable Long id){
        Article articleUpdate = articleService.updateArticle(articleReq,id);
        HttpStatus status = (articleUpdate != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
        return new ResponseEntity<>(articleUpdate,status);
    }

    @DeleteMapping("articles/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity<>(Boolean.toString(articleService.deleteArticle(id)),HttpStatus.NO_CONTENT);
    }

}
