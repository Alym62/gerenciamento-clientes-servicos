package com.full.servicos.dto.requests;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class ServicoPrestadoPostDTO {
    @NotBlank(message = "O campo não pode ser vazio.")
    private String descricao;

    private Long idCliente;

    @NotBlank(message = "O campo não pode ser vazio.")
    private String valor;

    @FutureOrPresent(message = "Coloque a data atual ou uma futura.")
    private LocalDate data;
}
