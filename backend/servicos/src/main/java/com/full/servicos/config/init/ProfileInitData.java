package com.full.servicos.config.init;

import com.full.servicos.domain.Usuario;
import com.full.servicos.domain.enums.Role;
import com.full.servicos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@Profile("test")
public class ProfileInitData implements CommandLineRunner {
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        Usuario usuario = new Usuario();
        usuario.setUsername("admin");
        usuario.setPassword(passwordEncoder.encode("123"));
        usuario.setRole(Set.of(Role.ADMIN));

        repository.save(usuario);
    }
}
