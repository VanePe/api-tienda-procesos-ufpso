package com.ufpso.tienda.service;

import com.ufpso.tienda.exception.AlreadyExistsException;
import com.ufpso.tienda.exception.NotFoundException;
import com.ufpso.tienda.model.User;
import com.ufpso.tienda.repository.UserRepository;
import com.ufpso.tienda.util.ExepctionsConstans;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //Metodo para insertar
    public User createUser(User userReq){
        Optional<User> existingUserByEmail = userRepository.findByEmail(userReq.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new AlreadyExistsException(ExepctionsConstans.USER_EMAIL_EXISTS.getMessage());
        }
        userReq.setPassword(passwordEncoder.encode(userReq.getPassword()));
        return userRepository.save(userReq);
    }

    public User getUserById(Long id){
        if(id == null){
            throw new NotFoundException(ExepctionsConstans.USER_IS_NULL.getMessage());
        }
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            throw new NotFoundException(ExepctionsConstans.USER_NOT_FOUND.getMessage());
        }
        return user.get();
    }

    public List<User> findAllUsers(){
        return (List<User>) userRepository.findAll();
    }

    //Metodo para actializar un usuario
    public User updateUser(User userReq, Long id){
        Optional<User> userBd = userRepository.findById(id);
        if(userBd.isEmpty()){
            throw new NotFoundException("User not found");
        }
        if(!userBd.get().getEmail().equals(userReq.getEmail())){
            Optional<User> existingUserByEmail = userRepository.findByEmail(userReq.getEmail());
            if (existingUserByEmail.isPresent()) {
                throw new AlreadyExistsException(ExepctionsConstans.USER_EMAIL_EXISTS.getMessage());
            }
        }
        userBd.get().setFirstName(userReq.getFirstName());
        userBd.get().setLastName(userReq.getLastName());
        userBd.get().setPhone(userReq.getPhone());
        return userRepository.save(userBd.get());
    }

    //Metodo para eliminar de una base de datos
    public boolean deleteUser(Long id){
        Optional<User> userBd = userRepository.findById(id);
        if(userBd.isEmpty()){
            throw new NotFoundException(ExepctionsConstans.USER_NOT_FOUND.getMessage());
        }
        userRepository.delete(userBd.get());
        return true;
    }

}
