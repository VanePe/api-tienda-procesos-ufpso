package com.ufpso.tienda.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ufpso.tienda.util.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(
        name = "Users"
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {

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
    //@JsonIgnore
    @NotNull(message = "Password is required")
    @Size(min = 8, max = 255,message = "password min 8 characters and max 15")
    private String password;
    @Enumerated( EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Address> addressList;

    @Override
    public boolean isEnabled() {
        return true;
    }
}