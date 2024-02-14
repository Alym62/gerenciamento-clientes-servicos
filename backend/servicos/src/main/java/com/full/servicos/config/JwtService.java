package com.full.servicos.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.full.servicos.domain.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;

@Service
public class JwtService {
    private static final String JWT_SECRET = "W+jX#P9o2}40%#J#7e[qGZ)Ps1g6A5@>PVSoDIH+ls87zrd(Z$";

    private static final Integer EXPIRES_IN = 599;

    public HashMap<String, Object> gerarToken(Usuario usuario) {
        var algoritmo = gerarAlgorithm();

        var roles = usuario.getAuthorities()
                .stream().toList();

        var token = gerarConstrutorJwt()
                .withIssuer("udemy")
                .withSubject(usuario.getUsername())
                .withExpiresAt(getDataInspiracao())
                //.withClaim("roles", "ADMIN")
                .sign(algoritmo);

        var response = new HashMap<String, Object>();
        response.put("token", token);
        response.put("expires_in", getDataInspiracao());

        return response;
    }

    public String extrairUsuario(String jwtToken) {
        var algoritmo = gerarAlgorithm();

        return JWT.require(algoritmo)
                .withIssuer("udemy")
                .build()
                .verify(jwtToken)
                .getSubject();
    }

    private Algorithm gerarAlgorithm() {
        return Algorithm.HMAC256(JWT_SECRET);
    }

    private JWTCreator.Builder gerarConstrutorJwt() {
        return JWT.create();
    }

    private Instant getDataInspiracao() {
        return LocalDateTime.now().plusSeconds(EXPIRES_IN).toInstant(ZoneOffset.of("-03:00"));
    }
}
