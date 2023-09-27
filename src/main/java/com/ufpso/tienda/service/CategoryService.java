package com.ufpso.tienda.model.repository;

import com.ufpso.tienda.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;

    public Category createCategory(Category categoryReg){
        return categoryRepository.save(categoryReg);
    }

    public Category getCategoryById(Long id){
        return categoryRepository.findById(id).get();
    }

    public Category updateCategory(Category categoryReq, Long id){
        Optional<Category> categoryBd = categoryRepository.findById(id);
        if(categoryBd.isEmpty()){
            return false;
        }
        categoryBd.get().setNameCategory(categoryReq.getNameCategory());
        return categoryRepository.save(categoryBd.get());
    }

    public boolean deleteCategory(Long id){
        Optional<Category> categoryBd = categoryRepository.findById(id);
        if(categoryBd.isEmpty()){
            return false;
        }
        categoryRepository.delete(categoryBd.get());
        return true;
    }

    public List<Category> findAllCategory(){
        return (List<Category>) categoryRepository.findAll();
    }


}