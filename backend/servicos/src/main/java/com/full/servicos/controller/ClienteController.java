package com.full.servicos.controller;

import com.full.servicos.domain.Cliente;
import com.full.servicos.dto.requests.ClientePostDTO;
import com.full.servicos.dto.requests.ClientePutDTO;
import com.full.servicos.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("api/v1/cliente")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @GetMapping
    @ResponseStatus(code = OK)
    public Page<Cliente> listar(Pageable pageable) {
        return clienteService.listar(pageable);
    }

    @GetMapping("/listar")
    @ResponseStatus(code = OK)
    public List<Cliente> listarParaServico() {
        return clienteService.listarParaOServico();
    }

    @GetMapping("/todos")
    @ResponseStatus(code = OK)
    public Long listarQtdDeClientesNoSistema() {
        return clienteService.totalDeClientesRegistrados();
    }

    @GetMapping("/media")
    @ResponseStatus(code = OK)
    public Double mediaMensalDeClientes() {
        return clienteService.calcularMediaMensalDeClientes();
    }

    @GetMapping("/{id}")
    @ResponseStatus(code = OK)
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return new ResponseEntity<>(clienteService.findById(id), OK);
    }

    @PostMapping
    @ResponseStatus(code = CREATED)
    public ResponseEntity<Cliente> salvarCliente(@RequestBody @Valid ClientePostDTO clientePostDTO) {
        var clienteSalvo = clienteService.salvar(clientePostDTO);

        return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = OK)
    public ResponseEntity<Void> atualizarCliente(@PathVariable Long id, @RequestBody @Valid ClientePutDTO clientePutDTO) {
        clientePutDTO.setId(id);
        clienteService.atualizar(clientePutDTO);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = OK)
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.deletar(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
