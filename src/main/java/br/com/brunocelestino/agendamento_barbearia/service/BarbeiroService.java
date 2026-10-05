package br.com.brunocelestino.agendamento_barbearia.service;

import br.com.brunocelestino.agendamento_barbearia.data.entity.Barbeiro;
import br.com.brunocelestino.agendamento_barbearia.data.repository.BarbeiroRepository;
import br.com.brunocelestino.agendamento_barbearia.dto.BarbeiroDto;
import br.com.brunocelestino.agendamento_barbearia.dto.BarbeiroUpdateDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BarbeiroService {

    private final BarbeiroRepository barbeiroRepository;

    public Page<Barbeiro> findAllBarbeiros(Pageable page){
        return barbeiroRepository.findAll(page);
    }

    public Page<Barbeiro> findAllBarbeirosActivated(Pageable page){
        return barbeiroRepository.findAllByAtivoIsTrue(page);
    }

    public Barbeiro findById(UUID barbeiroId){
        return barbeiroRepository.findById(barbeiroId).orElseThrow();
    }

    @Transactional
    public void createBarbeiro(BarbeiroDto barbeiroDto){
        barbeiroRepository.save(new Barbeiro(null, barbeiroDto.nome(), barbeiroDto.telefone(), true));
    }

    @Transactional
    public BarbeiroDto updateBarbeiro(BarbeiroUpdateDto barbeiroUpdateDto){
        Barbeiro barbeiro = barbeiroRepository.findByTelefone(barbeiroUpdateDto.telefoneAntigo()).orElseThrow();

        barbeiro.setTelefone(barbeiroUpdateDto.telefone());
        barbeiroRepository.save(barbeiro);

        return new BarbeiroDto(barbeiro.getNome(), barbeiro.getTelefone());
    }

    @Transactional
    public void toggleBarbeiroStatus(UUID barbeiroId){
        barbeiroRepository.toggleBarbeiroStatus(barbeiroId);
    }

}
