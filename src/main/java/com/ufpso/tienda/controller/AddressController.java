package com.ufpso.tienda.controller;


import com.ufpso.tienda.model.Address;
import com.ufpso.tienda.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("address/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id){
        //return ResponseEntity.ok(addressService.getAddressById(id));
        return new ResponseEntity<>(addressService.getAddressById(id), HttpStatus.OK);
    }

    @GetMapping("address")
    public ResponseEntity<List<Address>> findAll(){
        return new ResponseEntity<>(addressService.finAllAddress(),HttpStatus.OK);
    }

    @PostMapping("address/{id}")
    public ResponseEntity<Address> create(@Validated @RequestBody Address address,@PathVariable Long id){
        return new ResponseEntity<>(addressService.createAddress(address,id),HttpStatus.CREATED);
    }

    @GetMapping("address/disabled/{id}")
    public ResponseEntity disabled(@PathVariable Long id){
        return ResponseEntity.ok(addressService.updateStatusAddress(id));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
