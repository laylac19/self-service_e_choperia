package com.cssp.choperiaselfservice.auth;

import com.cssp.choperiaselfservice.config.JwtService;
import com.cssp.choperiaselfservice.domain.Perfil;
import com.cssp.choperiaselfservice.domain.Usuario;
import com.cssp.choperiaselfservice.domain.enums.Role;
import com.cssp.choperiaselfservice.repository.UsuarioRepository;
import com.cssp.choperiaselfservice.service.dto.LoginDTO;
import com.cssp.choperiaselfservice.service.dto.UsuarioDTO;
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

    public AuthenticationResponse register(UsuarioDTO usuario) {
        Perfil perfilUser = new Perfil();
        Usuario user = Usuario.builder()
                .usuario(usuario.getUsuario())
                .nome(usuario.getNome())
                .senha(passwordEncoder.encode(usuario.getSenha()))
                .perfil(perfilUser)
                .ativo(true)
                .build();
        usuarioRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public LoginDTO authenticate(AuthenticationRequest authenticationRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()
                )
        );

        var usuario = usuarioRepository.findByUsuario(authenticationRequest.getUsername());
                //.orElseThrow(() -> new UsernameNotFoundException("Usuario nao encontrado"));

        LoginDTO loginData = new LoginDTO(
                usuario.getUsuario(),
                usuario.getNome(),
                usuario.getPerfil().getDescricao(),
                usuario.getPerfil().getId()
        );

        String jwtToken = jwtService.generateToken(usuario);

//        return AuthenticationResponse.builder()
//                .token(jwtToken)
//                .build();

        return loginData;
    }
}
