package com.cssp.choperiaselfservice.service;

import com.cssp.choperiaselfservice.auth.AuthenticationResponse;
import com.cssp.choperiaselfservice.config.JwtService;
import com.cssp.choperiaselfservice.domain.Perfil;
import com.cssp.choperiaselfservice.domain.Usuario;
import com.cssp.choperiaselfservice.domain.enums.Role;
import com.cssp.choperiaselfservice.repository.UsuarioRepository;
import com.cssp.choperiaselfservice.service.dto.UsuarioDTO;
import com.cssp.choperiaselfservice.service.dto.UsuarioListDTO;
import com.cssp.choperiaselfservice.service.exception.EntityNotFoundException;
import com.cssp.choperiaselfservice.service.mapper.UsuarioMapper;
import com.cssp.choperiaselfservice.service.util.MensagemUsuarioUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioService {
    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    public Usuario findEntity(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(MensagemUsuarioUtil.ENTITY_NOT_FOUND));
    }

    public Page<UsuarioListDTO> findAll(Pageable pageable) {
        return repository.listAll(pageable);
    }

    public UsuarioDTO findByID(Long id) {
        return mapper.toDto(findEntity(id));
    }

    public UsuarioDTO save(UsuarioDTO dto) {

        dto.setSenha(passwordEncoder.encode(dto.getSenha()));

        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public void delete(Long id) {
        Usuario user = findEntity(id);
        user.setAtivo(false);
        repository.save(user);
    }
}
