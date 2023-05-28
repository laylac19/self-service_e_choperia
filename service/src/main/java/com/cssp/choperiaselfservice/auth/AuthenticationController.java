package com.cssp.choperiaselfservice.auth;

import com.cssp.choperiaselfservice.domain.Usuario;
import com.cssp.choperiaselfservice.service.dto.LoginDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest){
        AuthenticationResponse usuario  = authenticationService.authenticate(authenticationRequest);
        return ResponseEntity.ok(usuario);
    }
}
