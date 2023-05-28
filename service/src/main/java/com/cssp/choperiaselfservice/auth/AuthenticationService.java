package com.cssp.choperiaselfservice.auth;

import com.cssp.choperiaselfservice.config.JwtService;
import com.cssp.choperiaselfservice.domain.Perfil;
import com.cssp.choperiaselfservice.domain.Usuario;
import com.cssp.choperiaselfservice.domain.enums.Role;
import com.cssp.choperiaselfservice.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        Usuario usuario = Usuario.builder()
                .usuario(registerRequest.getUsername())
                .nome(registerRequest.getName())
                .senha(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.ADMIN)
                .ativo(true)
                .build();
        usuarioRepository.save(usuario);

        String jwtToken = jwtService.generateToken(usuario);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        var usuario = usuarioRepository.findByUsuario(authenticationRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado"));


        String jwtToken = jwtService.generateToken(usuario);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
