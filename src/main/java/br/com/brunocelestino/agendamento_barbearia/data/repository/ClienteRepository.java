package br.com.brunocelestino.agendamento_barbearia.data.repository;

import br.com.brunocelestino.agendamento_barbearia.data.entity.Cliente;
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
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    Page<Cliente> findAll(@NonNull Pageable page);

    @Query("SELECT c FROM Cliente c WHERE c.ativo = true")
    Page<Cliente> findAllActivated(@NonNull Pageable page);

    Optional<Cliente> findById(@NonNull UUID id);

    boolean existsByEmail(String email);

    @Modifying
    @Query(value = "UPDATE clientes SET ativo = NOT ativo WHERE email = :email", nativeQuery = true)
    void toggleStatus(@Param("email")String email);
}
