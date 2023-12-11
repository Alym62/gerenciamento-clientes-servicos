package com.full.servicos.service;

import com.full.servicos.domain.Cliente;
import com.full.servicos.dto.ClientePostDTO;
import com.full.servicos.dto.ClientePutDTO;
import com.full.servicos.exception.BadRequestException;
import com.full.servicos.repository.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Page<Cliente> listar(Pageable pageable){
        return clienteRepository.findAll(pageable);
    }

    public Cliente findById(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Esse cliente não existe."));
    }

    @Transactional
    public Cliente salvar(ClientePostDTO clientePostDTO){
        Cliente cliente = Cliente.builder()
                .id(clientePostDTO.getId()).nome(clientePostDTO.getNome())
                .cpf(clientePostDTO.getCpf()).dataCadastro(clientePostDTO.getDataCadastro()).build();

        return clienteRepository.save(cliente);
    }

    @Transactional
    public void atualizar(ClientePutDTO clientePutDTO){
        findById(clientePutDTO.getId());

        Cliente cliente = Cliente.builder()
                .id(clientePutDTO.getId()).nome(clientePutDTO.getNome())
                .cpf(clientePutDTO.getCpf()).build();

        clienteRepository.save(cliente);
    }

    public void deletar(Long id){
        //clienteRepository.delete(findById(id));
        clienteRepository.findById(id).map(cliente -> {
            clienteRepository.delete(cliente);
            return Void.TYPE;
        }).orElseThrow(() -> new BadRequestException("Esse cliente não existe."));
    }
}
