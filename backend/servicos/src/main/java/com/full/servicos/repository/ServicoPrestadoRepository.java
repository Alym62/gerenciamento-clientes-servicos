package com.full.servicos.repository;

import com.full.servicos.domain.ServicoPrestado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Long> {

    @Query("SELECT s FROM ServicoPrestado s JOIN s.cliente c " +
            "WHERE UPPER(c.nome) LIKE UPPER(:nome) AND MONTH(s.data) = :mes")
    List<ServicoPrestado> buscarPorNomeEMes(@Param("nome") String nome, @Param("mes") Integer mes);
}
