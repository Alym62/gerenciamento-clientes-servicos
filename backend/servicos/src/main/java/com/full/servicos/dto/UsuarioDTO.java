package com.full.servicos.dto;

import com.full.servicos.domain.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class UsuarioDTO {
    private String username;
    private String password;
    private Set<Role> roles;
}
