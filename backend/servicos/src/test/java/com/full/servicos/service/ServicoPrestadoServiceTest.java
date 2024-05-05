package com.full.servicos.service;

import com.full.servicos.domain.Cliente;
import com.full.servicos.domain.FileDB;
import com.full.servicos.domain.ServicoPrestado;
import com.full.servicos.dto.requests.ServicoPrestadoPostDTO;
import com.full.servicos.repository.ClienteRepository;
import com.full.servicos.repository.FileDBRepository;
import com.full.servicos.repository.ServicoPrestadoRepository;
import com.full.servicos.util.BigDecimalConverter;
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
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class ServicoPrestadoServiceTest {
    @InjectMocks
    private ServicoPrestadoService service;

    @Mock
    private ServicoPrestadoRepository repository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private FileDBRepository fileDBRepository;

    @Mock
    private FileDBService fileDBService;

    @Mock
    private BigDecimalConverter converter;

    private Cliente cliente;
    private ServicoPrestado servicoPrestado;
    private FileDB file;
    private ServicoPrestadoPostDTO postDTO;

    @BeforeEach()
    void setUp() {
        byte[] image = null;
        file = new FileDB("test", "png", image);
        cliente = new Cliente(1L, "Daniel", "09956212172", LocalDate.now());
        servicoPrestado = new ServicoPrestado(1L, "Monitor", cliente, BigDecimal.valueOf(2.500), LocalDate.of(2024, 5, 5), file);

        fileDBRepository.save(file);
        clienteRepository.save(cliente);
        repository.save(servicoPrestado);
    }

    @Test
    @DisplayName("Teste unitário do método - FindAll com Pageable")
    void listar() {
        List<ServicoPrestado> servicos = List.of(servicoPrestado);
        Pageable pageable = PageRequest.of(0, 10);
        Page<ServicoPrestado> page = new PageImpl<>(servicos, pageable, servicos.size());

        when(repository.findAll(pageable)).thenReturn(page);

        var response = service.listar(pageable);
        var expected = servicos.size();

        assertEquals(expected, response.getTotalElements());
        assertEquals(servicos, response.getContent());
    }

    @Test
    @DisplayName("Teste unitário do método - BuscarNomePorMes")
    void pesquisa() {
        var nome = cliente.getNome();
        var mes = 5;

        when(repository.buscarPorNomeEMes(nome, mes)).thenReturn(List.of(servicoPrestado));

        var response = service.pesquisa(nome, mes);
        var expected = List.of(servicoPrestado);

        assertEquals(expected.size(), response.size());
    }

    @Test
    @DisplayName("Teste unitário do método - ValorUltimoServico")
    void valorUltimoServico() {
        when(repository.valorUltimoServico()).thenReturn(servicoPrestado.getValor());

        var response = service.valorUltimoServico();
        var expected = Optional.of(servicoPrestado.getValor());

        assertEquals(expected, response);
    }

    @Test
    @DisplayName("Teste unitário do método - Save")
    void salvar() {
        MultipartFile multipartFile = mock(MultipartFile.class);

        postDTO = new ServicoPrestadoPostDTO(servicoPrestado.getDescricao(), servicoPrestado.getCliente().getId(),
                "2.500", servicoPrestado.getData());

        when(clienteRepository.findById(any())).thenReturn(Optional.of(cliente));
        when(fileDBService.store(multipartFile)).thenReturn(file);

        service.salvar(postDTO, multipartFile);

        verify(repository, times(2)).save(any(ServicoPrestado.class));

    }
}