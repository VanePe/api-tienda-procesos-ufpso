package com.ufpso.tienda.service;

import com.ufpso.tienda.auth.AuthResponse;
import com.ufpso.tienda.auth.LoginRequest;
import com.ufpso.tienda.auth.RegisterRequest;
import com.ufpso.tienda.exception.NotFoundException;
import com.ufpso.tienda.model.User;
import com.ufpso.tienda.repository.AuthRepository;
import com.ufpso.tienda.util.ExepctionsConstans;
import com.ufpso.tienda.util.Role;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails user = authRepository.findByEmail(request.getEmail()).
                orElseThrow(() -> new NotFoundException(ExepctionsConstans.CREDENTIAL_INVALID.getMessage()));
        String token = jwtService.getToken(user);
        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder().
                firstName(request.getFirstName()).
                lastName(request.getLastName()).
                phone(request.getPhone()).
                document(request.getDocument()).
                email(request.getEmail()).
                password(passwordEncoder.encode(request.getPassword())).
                role(Role.USER).build();
        authRepository.save(user);
        return AuthResponse.builder().token(jwtService.getToken(user)).build();
    }
}
