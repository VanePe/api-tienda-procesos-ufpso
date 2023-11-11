package com.ufpso.tienda.service;

import com.ufpso.tienda.exception.NotFoundException;
import com.ufpso.tienda.model.User;
import com.ufpso.tienda.repository.UserRepository;
import com.ufpso.tienda.util.ExepctionsConstans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //Metodo para insertar
    public User createUser(User userReq){
        return userRepository.save(userReq);
    }

    public User getUserById(Long id){
        if(id == null)
            throw new RuntimeException(ExepctionsConstans.USER_NOT_FOUND.getMessage());
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(ExepctionsConstans.USER_NOT_FOUND.getMessage()));
    }

    public List<User> findAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    //Metodo para actializar un usuario
    public User updateUser(User userReq, Long id){
        Optional<User> userBd = userRepository.findById(id);
        if(userBd.isEmpty())
            throw new RuntimeException(ExepctionsConstans.USER_NOT_FOUND.getMessage());
        userBd.get().setFirstName(userReq.getFirstName());
        userBd.get().setLastName(userReq.getLastName());
        userBd.get().setPhone(userReq.getPhone());
        return userRepository.save(userBd.get());
    }

    //Metodo para eliminar de una base de datos
    public boolean deleteUser(Long id){
        Optional<User> userBd = userRepository.findById(id);
        if(userBd.isEmpty())
            throw new RuntimeException(ExepctionsConstans.USER_NOT_FOUND.getMessage());
        userRepository.deleteById(userBd.get().getId());
        return true;
    }

}
