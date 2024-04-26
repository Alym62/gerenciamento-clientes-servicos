package com.full.servicos.service;

import com.full.servicos.domain.Cliente;
import com.full.servicos.dto.requests.ClientePostDTO;
import com.full.servicos.repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class ClienteServiceTest {
    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    private Cliente user;
    private ClientePostDTO dto;

    @BeforeEach
    void setUp() {
        user = new Cliente(1L, "Daniel", "94676336047", LocalDate.now());
        repository.save(user);
    }

    @Test
    void listar() {
    }

    @Test
    void listarParaOServico() {
    }

    @Test
    @DisplayName("Teste unitário método - FindById")
    void findById() {
        var id = 1L;

        when(repository.findById(id)).thenReturn(Optional.of(user));

        var response = service.findById(id);
        var expected = new Cliente(1L, "Daniel", "94676336047", LocalDate.now());
        Assertions.assertEquals(response, expected);
    }

    @Test
    void totalDeClientesRegistrados() {
    }

    @Test
    void calcularMediaMensalDeClientes() {
    }

    @Test
    @DisplayName("Teste unitário método - Save")
    void salvar() {
        dto = new ClientePostDTO(user.getId(), user.getNome(), user.getCpf(), user.getDataCadastro());

        when(repository.save(user)).thenReturn(user);

        var response = service.salvar(dto);
        var expected = new Cliente(1L, "Daniel", "94676336047", LocalDate.now());

        Assertions.assertEquals(expected, response);
    }

    @Test
    void atualizar() {
    }

    @Test
    void deletar() {
    }
}