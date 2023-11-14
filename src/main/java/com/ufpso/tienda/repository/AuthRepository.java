package com.ufpso.tienda.repository;

import com.ufpso.tienda.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByEmail(String email);
}
