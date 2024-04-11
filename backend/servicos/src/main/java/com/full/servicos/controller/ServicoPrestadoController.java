package com.full.servicos.controller;

import com.full.servicos.domain.ServicoPrestado;
import com.full.servicos.dto.requests.ServicoPrestadoPostDTO;
import com.full.servicos.service.ServicoPrestadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

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
        return servicoPrestadoService.pesquisa("%" + nome + "%", mes);
    }

    @GetMapping("/valor")
    @ResponseStatus(code = OK)
    public Object valorDoUltimoServicoPrestado() {
        return servicoPrestadoService.valorUltimoServico();
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<ServicoPrestado> salvarServico(
            @Valid @RequestPart("data") ServicoPrestadoPostDTO servicoPrestadoPostDTO,
            @RequestPart("file") MultipartFile file
    ) {
        var servicoSalvo = servicoPrestadoService.salvar(servicoPrestadoPostDTO, file);

        return new ResponseEntity<>(servicoSalvo, HttpStatus.CREATED);
    }
}
