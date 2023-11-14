package com.ufpso.tienda.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Street address is required")
    @Size(max = 255,message = "Street address max 40 character")
    private String streetAddress;
    @NotNull(message = "City is required")
    @Size(max = 255, message = "City max 255 characters")
    private String city;
    @NotNull(message = "State is required")
    @Size(max = 100, message = "State max 100 characters")
    private String state;
    @NotNull(message = "Postal code is required")
    @Size(max = 10, message = "Postal code max 10 characters")
    private String postalCode;
    private Boolean status = Boolean.TRUE;

    @ManyToOne
    @JoinColumn(name = "id_user",referencedColumnName = "id")
    private User user;

}
