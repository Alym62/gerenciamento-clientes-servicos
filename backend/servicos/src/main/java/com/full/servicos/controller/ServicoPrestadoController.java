package com.full.servicos.controller;

import com.full.servicos.domain.ServicoPrestado;
import com.full.servicos.dto.ServicoPrestadoPostDTO;
import com.full.servicos.service.ServicoPrestadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/v1/servico")
@RequiredArgsConstructor
public class ServicoPrestadoController {
    private final ServicoPrestadoService servicoPrestadoService;

    @GetMapping
    public List<ServicoPrestado> pesquisarServico(
            @RequestParam(value = "nome", required = false, defaultValue = "") String nome,
            @RequestParam(value = "mes", required = false) Integer mes
    ) {
        return servicoPrestadoService.pesquisa("%" + nome + "%", + mes);
    }

    @GetMapping("/valor")
    @ResponseStatus(code = OK)
    public BigDecimal valorDoUltimoServicoPrestado() {
        return servicoPrestadoService.valorUltimoServico();
    }

    @PostMapping
    public ResponseEntity<ServicoPrestado> salvarServico(@Valid @RequestBody ServicoPrestadoPostDTO servicoPrestadoPostDTO) {
        var servicoSalvo = servicoPrestadoService.salvar(servicoPrestadoPostDTO);

        return new ResponseEntity<>(servicoSalvo, HttpStatus.CREATED);
    }
}
