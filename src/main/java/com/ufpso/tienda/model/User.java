package com.ufpso.tienda.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Firstname is required")
    @Size(max = 255,message = "Firtsname max 255 characters")
    private String firstName;
    @NotNull(message = "Lastname is required")
    @Size(max = 255,message = "Lastname max 255 characters")
    private String lastName;
    private String phone;
    @NotNull(message = "Document is required")
    @Size(min = 5, max = 15,message = "Document min 5 characters and max 15")
    private String document;
    @NotNull(message = "Email is required")
    @Email(message = "email no valid")
    private String email;

    @JsonIgnore
    @NotNull(message = "Password is required")
    @Size(min = 8, max = 15,message = "password min 8 characters and max 15")
    private String password;

}