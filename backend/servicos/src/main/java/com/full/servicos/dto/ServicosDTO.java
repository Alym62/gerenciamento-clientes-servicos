package com.full.servicos.dto;

import com.full.servicos.domain.Cliente;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
public class ServicosDTO {
    private Long id;
    private String descricao;
    private Cliente cliente;
    private BigDecimal valor;
}
