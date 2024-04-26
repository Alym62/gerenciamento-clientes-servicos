package com.full.servicos.service;

import com.full.servicos.domain.Cliente;
import com.full.servicos.dto.requests.ClientePostDTO;
import com.full.servicos.dto.requests.ClientePutDTO;
import com.full.servicos.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public Page<Cliente> listar(Pageable pageable){
        return clienteRepository.findAll(pageable);
    }

    public List<Cliente> listarParaOServico(){
        return clienteRepository.findAll();
    }

    public Cliente findById(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long totalDeClientesRegistrados() {
        return clienteRepository.count();
    }

    public Double calcularMediaMensalDeClientes() {
        LocalDate dataAtual = LocalDate.now();
        LocalDate primeiroDiaDoMesAtual = dataAtual.withDayOfMonth(1);
        LocalDate ultimoDiaDoMesAtual = dataAtual.withDayOfMonth(dataAtual.lengthOfMonth());

        List<Cliente> clientesNoMes = clienteRepository.findByDataCadastroBetween(primeiroDiaDoMesAtual, ultimoDiaDoMesAtual);

        if (clientesNoMes.isEmpty())
            return 0.0;

        double totalClientesNoMes = clientesNoMes.size();
        double totalDiasNoMes = dataAtual.lengthOfMonth();

        return totalClientesNoMes / totalDiasNoMes;
    }

    @Transactional
    public Cliente salvar(ClientePostDTO clientePostDTO){
        if (clienteRepository.existeCpf(clientePostDTO.getCpf())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esse CPF já existe.");
        }

        Cliente cliente = Cliente.builder()
                .id(clientePostDTO.getId()).nome(clientePostDTO.getNome())
                .cpf(clientePostDTO.getCpf()).dataCadastro(clientePostDTO.getDataCadastro()).build();

        return clienteRepository.save(cliente);
    }

    @Transactional
    public void atualizar(ClientePutDTO clientePutDTO){
        clienteRepository.findById(clientePutDTO.getId())
                .map(cliente -> {
                    cliente.setNome(clientePutDTO.getNome());
                    cliente.setCpf(clientePutDTO.getCpf());
                    return clienteRepository.save(cliente);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    @Transactional
    public void deletar(Long id){
        clienteRepository.findById(id).map(cliente -> {
            clienteRepository.delete(cliente);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }
}
