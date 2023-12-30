package com.full.servicos.repository;

import com.full.servicos.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT COUNT(c) > 0 FROM clientes c WHERE c.cpf = :cpf")
    boolean existeCpf(@Param("cpf") String cpf);
}
