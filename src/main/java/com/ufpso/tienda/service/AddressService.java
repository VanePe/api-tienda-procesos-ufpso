package com.ufpso.tienda.service;

import com.ufpso.tienda.exception.NotFoundException;
import com.ufpso.tienda.model.Address;
import com.ufpso.tienda.model.User;
import com.ufpso.tienda.repository.AddressRepository;
import com.ufpso.tienda.util.ExepctionsConstans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserService userService;

    public Address createAddress(Address address, Long idUser){
        User userBd = userService.getUserById(idUser);
        address.setUser(userBd);
        return addressRepository.save(address);
    }

    public Address updateStatusAddress(Long id){
        Address addressBd = this.getAddressById(id);
        addressBd.setStatus(Boolean.FALSE);
        return  addressRepository.save(addressBd);
    }

    public List<Address> finAllAddress(){
        return  (List<Address>) addressRepository.findAll();
    }

    public Address getAddressById(Long id){
        if( id == null)
            throw new NotFoundException(ExepctionsConstans.ADDRESS_IS_NULL.getMessage());
        Optional<Address> addressBd = addressRepository.findById(id);
        if(addressBd.isEmpty())
            throw new NotFoundException(ExepctionsConstans.ADDRESS_NOT_FOUND.getMessage());
        return addressBd.get();
    }
}
