package br.com.brunocelestino.agendamento_barbearia.data.repository;

import br.com.brunocelestino.agendamento_barbearia.data.entity.Agendamento;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, UUID> {

    Page<Agendamento> findAll(@NonNull Pageable page);

    @Query("SELECT a FROM Agendamento a WHERE a.cliente.id = :id")
    Page<Agendamento> findAllByCliente(@Param("id") UUID clienteId, Pageable page);


}
