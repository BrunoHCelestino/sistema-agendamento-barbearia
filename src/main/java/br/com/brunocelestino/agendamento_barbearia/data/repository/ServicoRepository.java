package br.com.brunocelestino.agendamento_barbearia.data.repository;

import br.com.brunocelestino.agendamento_barbearia.data.entity.Servico;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ServicoRepository extends JpaRepository<Servico, UUID> {

    Page<Servico> findAll(@NonNull Pageable page);

    Page<Servico> findAllByAtivoTrue(@NonNull Pageable page);

    @Modifying
    @Query(value = "UPDATE servicos SET ativo = NOT ativo WHERE id = :id", nativeQuery = true)
    void toggleServicoStatus(UUID id);
}
