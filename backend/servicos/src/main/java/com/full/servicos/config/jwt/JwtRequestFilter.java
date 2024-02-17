package com.full.servicos.config.jwt;

import com.full.servicos.service.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {
    private final UsuarioService usuarioService;

    private final JwtService jwtService;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return (request.getRequestURI().startsWith("/api/v1/auth"));
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String requestToken = request.getHeader("Authorization");
        String token = null;
        String username = null;

        System.out.println(requestToken);

        if (requestToken != null && requestToken.startsWith("Bearer ")) {
            token = requestToken.substring(7);
            username = jwtService.extrairUsuario(token);
        } else {
            throw new RuntimeException("O token não começa com [Bearer].");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var usuario = usuarioService.loadUserByUsername(username);

            var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(usuario, null,
                    usuario.getAuthorities());

            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }

        filterChain.doFilter(request, response);
    }
}
