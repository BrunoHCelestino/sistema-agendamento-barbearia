package br.com.brunocelestino.agendamento_barbearia.service;

import br.com.brunocelestino.agendamento_barbearia.data.entity.Servico;
import br.com.brunocelestino.agendamento_barbearia.data.repository.ServicoRepository;
import br.com.brunocelestino.agendamento_barbearia.dto.ServicoDto;
import br.com.brunocelestino.agendamento_barbearia.dto.ServicoUpdateDto;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public Page<Servico> findAllServicos(Pageable page) {
        return servicoRepository.findAll(page);
    }

    public Servico findById(UUID servicoId){
        return servicoRepository.findById(servicoId)
                .orElseThrow();
    }

    public Page<Servico> findAllServicosActivated(Pageable page) {
        return servicoRepository.findAllByAtivoTrue(page);
    }

    @Transactional
    public void createServico(@Valid @NonNull ServicoDto servicoDto){
        servicoRepository.save(new Servico
                (null, servicoDto.nome(), servicoDto.descricao(), servicoDto.preco(), servicoDto.duracaoEmMinutos(), true));
    }

    @Transactional
    public void toggleServicoStatus(UUID id) {
        servicoRepository.toggleServicoStatus(id);
    }

    @Transactional
    public ServicoDto updateServico(UUID servicoId, ServicoUpdateDto servicoUpdateDto){
        Servico servicoAntigo = servicoRepository.findById(servicoId).orElseThrow();

        Servico servicoNovo = Servico.builder()
                .id(servicoAntigo.getId())
                .nome(servicoUpdateDto.nome())
                .descricao(servicoUpdateDto.descricao())
                .preco(servicoUpdateDto.preco())
                .duracaoEmMinutos(servicoUpdateDto.duracaoEmMinutos())
                .build();

        if (servicoNovo.getNome().isBlank()){
            servicoNovo.setNome(servicoAntigo.getNome());
        }

        if (servicoNovo.getDescricao().isBlank()){
            servicoNovo.setDescricao(servicoAntigo.getDescricao());
        }

        if (isPriceNullOrZero(servicoNovo.getPreco())){
            servicoNovo.setPreco(servicoAntigo.getPreco());
        }

        if (isDurationInMinutesNullOrZero(servicoNovo.getDuracaoEmMinutos())){
            servicoNovo.setDuracaoEmMinutos(servicoAntigo.getDuracaoEmMinutos());
        }

        servicoRepository.save(servicoNovo);

        return ServicoDto.builder()
                .nome(servicoNovo.getNome())
                .descricao(servicoNovo.getDescricao())
                .preco(servicoNovo.getPreco())
                .duracaoEmMinutos(servicoNovo.getDuracaoEmMinutos())
                .build();
    }

    private boolean isPriceNullOrZero(BigDecimal valor) {
        return valor == null || valor.compareTo(BigDecimal.ZERO) == 0;
    }

    private boolean isDurationInMinutesNullOrZero(Integer valor) {
        return valor == null || valor.compareTo(0) == 0;
    }
}
