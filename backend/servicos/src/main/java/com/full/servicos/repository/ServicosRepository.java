package com.full.servicos.repository;

import com.full.servicos.domain.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicosRepository extends JpaRepository<Servico, Long> {
}
