package com.full.servicos.controller;

import com.full.servicos.config.jwt.JwtService;
import com.full.servicos.domain.Usuario;
import com.full.servicos.dto.UsuarioDTO;
import com.full.servicos.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final JwtService jwtService;

    private final AuthenticationManager authManager;

    private final UsuarioService usuarioService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/registro")
    public ResponseEntity<?> registrar(@RequestBody UsuarioDTO usuarioDTO) {
        var user = Usuario.builder()
                .username(usuarioDTO.getUsername())
                .password(passwordEncoder.encode(usuarioDTO.getPassword()))
                .build();

        usuarioService.salvarUsuario(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioDTO usuarioDTO) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(usuarioDTO.getUsername(), usuarioDTO.getPassword()));

        var user = usuarioService.loadUserByUsername(usuarioDTO.getUsername());

        return ResponseEntity.ok(jwtService.gerarToken(user));
    }
}
