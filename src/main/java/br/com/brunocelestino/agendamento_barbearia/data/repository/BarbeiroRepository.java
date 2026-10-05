package br.com.brunocelestino.agendamento_barbearia.data.repository;

import br.com.brunocelestino.agendamento_barbearia.data.entity.Barbeiro;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BarbeiroRepository extends JpaRepository<Barbeiro, UUID> {

    Page<Barbeiro> findAll(@NonNull Pageable page);

    Page<Barbeiro> findAllByAtivoIsTrue(Pageable pageable);

    Optional<Barbeiro> findByTelefone(String telefone);

    @Modifying
    @Query(value = "UPDATE barbeiros SET ativo = NOT ativo WHERE id = :id", nativeQuery = true)
    void toggleBarbeiroStatus(@Param("id")UUID id);

}
