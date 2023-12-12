package com.ufpso.tienda.service;


import com.ufpso.tienda.dto.AuthResponse;
import com.ufpso.tienda.dto.LoginRequest;
import com.ufpso.tienda.exception.AlreadyExistsException;
import com.ufpso.tienda.exception.AuthenticationFailedException;
import com.ufpso.tienda.exception.NotFoundException;
import com.ufpso.tienda.model.Role;
import com.ufpso.tienda.model.User;
import com.ufpso.tienda.repository.UserRepository;
import com.ufpso.tienda.util.ExepctionsConstans;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(), request.getPassword()));
        } catch (Exception e) {
            throw new AuthenticationFailedException(ExepctionsConstans.CREDENTIAL_INVALID.getMessage());
        }
        UserDetails user = userRepository.findByEmail(request.getEmail()).
                orElseThrow(() -> new NotFoundException(ExepctionsConstans.CREDENTIAL_INVALID.getMessage()));
        String token = jwtService.getToken(user);
        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse register(@Valid User request) {
        Optional<User> existingUserByEmail = userRepository.findByEmail(request.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new AlreadyExistsException(ExepctionsConstans.USER_EMAIL_EXISTS.getMessage());
        }

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .document(request.getDocument())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);
        return AuthResponse.builder().token(jwtService.getToken(user)).build();
    }
}
