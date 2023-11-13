package com.ufpso.tienda.repository;


import com.ufpso.tienda.model.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address,Long> {
}
