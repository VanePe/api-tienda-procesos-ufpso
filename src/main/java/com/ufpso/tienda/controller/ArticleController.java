package com.ufpso.tienda.controller;

import com.ufpso.tienda.model.Article;
import com.ufpso.tienda.service.ArticleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("articles/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id){
        return ResponseEntity.ok(articleService.getArticleById(id));
    }

    @GetMapping("articles")
    public ResponseEntity<List<Article>> findAll(){
        return new ResponseEntity<>(articleService.findAllArticles(),HttpStatus.OK);
    }

    @PostMapping("articles/{id}")
    public ResponseEntity<Article> create(@Valid @RequestBody Article article, @PathVariable Long idCategory){
        return new ResponseEntity<>(articleService.createArticle(article, idCategory),HttpStatus.CREATED);
    }

    @PutMapping("articles/{id}")
    public ResponseEntity<Article> update(@Valid @RequestBody Article articleReq, @PathVariable Long id){
        return new ResponseEntity<>(articleService.updateArticle(articleReq, id), HttpStatus.OK);
    }

    @DeleteMapping("articles/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity(articleService.deleteArticle(id),HttpStatus.NO_CONTENT);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        final Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
