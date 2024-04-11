package com.full.servicos.repository;

import com.full.servicos.domain.ServicoPrestado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Long> {

    @Query("SELECT s FROM ServicoPrestado s JOIN s.cliente c " +
            "WHERE UPPER(c.nome) LIKE UPPER(:nome) AND MONTH(s.data) = :mes")
    List<ServicoPrestado> buscarPorNomeEMes(@Param("nome") String nome, @Param("mes") Integer mes);

    @Query("SELECT s.valor, s.file FROM ServicoPrestado s WHERE s.id = (SELECT MAX(sp.id) FROM ServicoPrestado sp)")
    Object valorUltimoServico();
}
