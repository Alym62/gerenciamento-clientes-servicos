package com.full.servicos.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Builder
@Data
public class ClientePostDTO {
    private Long id;

    @NotBlank(message = "Por favor, preencha o nome.")
    private String nome;

    @CPF(message = "O CPF precisa ser válido e único.")
    private String cpf;

    private LocalDate dataCadastro;
}
