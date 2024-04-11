package com.full.servicos.service;

import com.full.servicos.domain.Cliente;
import com.full.servicos.domain.FileDB;
import com.full.servicos.domain.ServicoPrestado;
import com.full.servicos.dto.requests.ServicoPrestadoPostDTO;
import com.full.servicos.repository.ClienteRepository;
import com.full.servicos.repository.ServicoPrestadoRepository;
import com.full.servicos.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ServicoPrestadoService {
    private final ServicoPrestadoRepository servicoPrestadoRepository;
    private final ClienteRepository clienteRepository;
    private final BigDecimalConverter bigDecimalConverter;
    private final FileDBService fileDBService;

    public Page<ServicoPrestado> listar(Pageable pageable){
        return servicoPrestadoRepository.findAll(pageable);
    }

    public List<ServicoPrestado> pesquisa(String nome, Integer mes){
        return servicoPrestadoRepository.buscarPorNomeEMes(nome, mes);
    }

    public Object valorUltimoServico() {
        var valor = servicoPrestadoRepository.valorUltimoServico();
        if (valor != null)
            return valor;
        else
            throw new RuntimeException("Algum erro aconteceu ao buscar o ultimo serviço prestado");
    }

    public ServicoPrestado salvar(ServicoPrestadoPostDTO servicoPrestadoPostDTO, MultipartFile file) {
        Long idCliente = servicoPrestadoPostDTO.getIdCliente();

        Cliente clienteId = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esse cliente não existe."));

        FileDB fileDB = fileDBService.store(file);

        ServicoPrestado servico = ServicoPrestado.builder()
                .descricao(servicoPrestadoPostDTO.getDescricao())
                .data(servicoPrestadoPostDTO.getData())
                .cliente(clienteId)
                .valor(bigDecimalConverter.converter(servicoPrestadoPostDTO.getValor()))
                .file(fileDB)
                .build();

        return servicoPrestadoRepository.save(servico);
    }
}
