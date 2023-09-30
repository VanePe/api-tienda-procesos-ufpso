package com.ufpso.tienda.controller;

import com.ufpso.tienda.model.Category;
import com.ufpso.tienda.service.CategoryService;
import com.ufpso.tienda.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController{
    @Autowired
    private CategoryService categoryService;

    @GetMapping("categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PostMapping("categories")
    public ResponseEntity<Category> create(@RequestBody Category category){
        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);
    }

    @PutMapping("categories/{id}")
    public ResponseEntity<Category> update(@RequestBody Category category, @PathVariable Long id){
        return new ResponseEntity<>(categoryService.updateCategory(category, id), HttpStatus.OK);
    }

    @DeleteMapping("categories/{id}")
    public ResponseEntity<String> delete(Long id){
        return new ResponseEntity<>(Boolean.toString(categoryService.deleteCategory(id)), HttpStatus.NO_CONTENT);
    }

    @GetMapping("categories")
    public ResponseEntity<List<Category>> findAll(){
        return ResponseEntity.ok(categoryService.findAllCategory());
    }

}