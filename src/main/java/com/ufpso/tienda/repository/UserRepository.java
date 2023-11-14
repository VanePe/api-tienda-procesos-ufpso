package com.ufpso.tienda.repository;
import com.ufpso.tienda.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

}
