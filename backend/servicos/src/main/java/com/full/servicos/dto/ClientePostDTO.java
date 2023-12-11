package com.full.servicos.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

    @CPF(message = "O CPF precisa ser válido.")
    @Min(value = 11, message = "O CPF deve conter apenas 11 números.")
    private String cpf;

    private LocalDate dataCadastro;
}
