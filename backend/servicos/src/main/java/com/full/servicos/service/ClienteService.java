package com.full.servicos.service;

import com.full.servicos.domain.Cliente;
import com.full.servicos.dto.ClientePostDTO;
import com.full.servicos.dto.ClientePutDTO;
import com.full.servicos.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    public Cliente salvar(ClientePostDTO clientePostDTO){
        if (clienteRepository.existeCpf(clientePostDTO.getCpf())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Esse CPF já existe.");
        }

        Cliente cliente = Cliente.builder()
                .id(clientePostDTO.getId()).nome(clientePostDTO.getNome())
                .cpf(clientePostDTO.getCpf()).dataCadastro(clientePostDTO.getDataCadastro()).build();

        return clienteRepository.save(cliente);
    }

    public void atualizar(ClientePutDTO clientePutDTO){
        clienteRepository.findById(clientePutDTO.getId())
                .map(cliente -> {
                    cliente.setNome(clientePutDTO.getNome());
                    cliente.setCpf(clientePutDTO.getCpf());
                    return clienteRepository.save(cliente);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }

    public void deletar(Long id){
        clienteRepository.findById(id).map(cliente -> {
            clienteRepository.delete(cliente);
            return Void.TYPE;
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    }
}
