package br.com.brunocelestino.agendamento_barbearia.data.repository;

import br.com.brunocelestino.agendamento_barbearia.data.entity.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BarbeiroRepository extends JpaRepository<Barbeiro, UUID> {
}
