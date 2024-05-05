package com.full.servicos.service;

import com.full.servicos.domain.Cliente;
import com.full.servicos.dto.requests.ClientePostDTO;
import com.full.servicos.dto.requests.ClientePutDTO;
import com.full.servicos.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class ClienteServiceTest {
    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    private Cliente user;
    private ClientePostDTO dto;
    private ClientePutDTO putDTO;

    @BeforeEach
    void setUp() {
        user = new Cliente(1L, "Daniel", "94676336047", LocalDate.now());
        repository.save(user);
    }

    @Test
    @DisplayName("Teste unitário método - FindAll com Pageable")
    void listar() {
        List<Cliente> clientes = List.of(user);
        Pageable pageable = PageRequest.of(0, 10);
        Page<Cliente> page = new PageImpl<>(clientes, pageable, clientes.size());

        when(repository.findAll(pageable)).thenReturn(page);

        var response = service.listar(pageable);
        var expected = clientes.size();

        assertEquals(expected, response.getTotalElements());
        assertEquals(clientes, response.getContent());
    }

    @Test
    @DisplayName("Teste unitário do método - FindAll com List")
    void listarParaOServico() {
        List<Cliente> clientes = List.of(user);

        when(repository.findAll()).thenReturn(clientes);

        var response = service.listarParaOServico();
        var expected = clientes;

        assertEquals(response, expected);
    }

    @Test
    @DisplayName("Teste unitário método - FindById")
    void findById() {
        var id = 1L;

        when(repository.findById(id)).thenReturn(Optional.of(user));

        var response = service.findById(id);
        var expected = new Cliente(1L, "Daniel", "94676336047", LocalDate.now());

        assertEquals(response, expected);
    }

    @Test
    @DisplayName("Teste unitário método - Count")
    void totalDeClientesRegistrados() {
        var resultado = 1L;

        when(repository.count()).thenReturn(resultado);

        var response = service.totalDeClientesRegistrados();

        verify(repository, times(1)).count();

        assertEquals(resultado, response);
    }

    @Test
    @DisplayName("Teste unitário método - FindByDataCadastroBetween")
    void calcularMediaMensalDeClientes() {
        LocalDate dataAtual = LocalDate.now();
        LocalDate primeiroDiaMes = dataAtual.withDayOfMonth(1);
        LocalDate ultimoDiaMes = dataAtual.withDayOfMonth(dataAtual.lengthOfMonth());

        List<Cliente> clientes = List.of(user);

        when(repository.findByDataCadastroBetween(primeiroDiaMes, ultimoDiaMes))
                .thenReturn(clientes);

        Double response = service.calcularMediaMensalDeClientes();

        double totalDiasMes = dataAtual.lengthOfMonth();
        double totalClientesMes = clientes.size();
        double mediaEsperada = totalClientesMes / totalDiasMes;

        assertEquals(mediaEsperada, response);
    }

    @Test
    @DisplayName("Teste unitário método - Save")
    void salvar() {
        dto = new ClientePostDTO(user.getId(), user.getNome(), user.getCpf(), user.getDataCadastro());

        when(repository.save(user)).thenReturn(user);

        var response = service.salvar(dto);
        var expected = new Cliente(1L, "Daniel", "94676336047", LocalDate.now());

        assertEquals(response, expected);
    }

    @Test
    @DisplayName("Teste unitário método - Update")
    void atualizar() {
        String novoNome = "Gabriel";
        String novoCpf = "54654689044";
        Long id = 1L;
        putDTO = new ClientePutDTO(id, novoNome, novoCpf);

        Cliente clienteAtualizado = new Cliente(putDTO.getId(), putDTO.getNome(), putDTO.getCpf(), user.getDataCadastro());

        when(repository.findById(id)).thenReturn(Optional.of(user));

        when(repository.save(clienteAtualizado)).thenReturn(clienteAtualizado);

        service.atualizar(putDTO);

        verify(repository, times(1)).findById(id);

        assertEquals(clienteAtualizado.getNome(), user.getNome());
        assertEquals(clienteAtualizado.getCpf(), user.getCpf());
    }

    @Test
    @DisplayName("Teste unitário método - Delete")
    void deletar() {
        var id = 1L;

        when(repository.findById(id)).thenReturn(Optional.of(user));

        service.deletar(id);

        verify(repository, times(1)).delete(user);
    }

    @Test
    @DisplayName("Teste unitário método - Delete com Exception")
    void deletarWithException() {
        var id = 2L;

        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> service.deletar(id));
    }
}