package com.ufpso.tienda.service;

import com.ufpso.tienda.repository.CategoryRepository;
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

    //Metodo para crear
    public Category createCategory(Category categoryReg){
        return categoryRepository.save(categoryReg);
    }

    //Metodo para buscar
    public Category getCategoryById(Long id){
        if (id == null) {
            throw new NotFoundException(ExepctionsConstans.CATEGORY_NOT_FOUND.getMessage());
        }
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw  new NotFoundException(ExepctionsConstans.CATEGORY_NOT_FOUND.getMessage());
        }
        return categor.get();
    }

    //Metodo para actualizar
    public Category updateCategory(Category categoryReq, Long id){
        Optional<Category> categoryBd = categoryRepository.findById(id);
        if(categoryBd.isEmpty()){
            throw new NotFoundException(ExepctionsConstans.CATEGORY_NOT_FOUND.getMessage());
        }
        categoryBd.get().setNameCategory(categoryReq.getNameCategory());
        categoryBd.get().setDescriptionCategory(categoryReq.getDescriptionCategory);
        return categoryRepository.save(categoryBd.get());
    }

    //Metodo para eliminar
    public boolean deleteCategory(Long id){
        Optional<Category> categoryBd = categoryRepository.findById(id);
        if(categoryBd.isEmpty()){
            if(categoryBd.isEmpty()){
                throw  new NotFoundException(ExepctionsConstans.CATEGORY_NOT_FOUND.getMessage());
            };
        }
        categoryRepository.deleteById(categoryBd.get().getIdCategory());
        return true;
    }

    public List<Category> findAllCategory(){
        return (List<Category>) categoryRepository.findAll();
    }


}