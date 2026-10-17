package br.com.brunocelestino.agendamento_barbearia.service;

import br.com.brunocelestino.agendamento_barbearia.data.entity.Agendamento;
import br.com.brunocelestino.agendamento_barbearia.data.entity.Barbeiro;
import br.com.brunocelestino.agendamento_barbearia.data.entity.Cliente;
import br.com.brunocelestino.agendamento_barbearia.data.entity.Servico;
import br.com.brunocelestino.agendamento_barbearia.data.enums.StatusAgendamento;
import br.com.brunocelestino.agendamento_barbearia.data.repository.AgendamentoRepository;
import br.com.brunocelestino.agendamento_barbearia.data.repository.BarbeiroRepository;
import br.com.brunocelestino.agendamento_barbearia.data.repository.ClienteRepository;
import br.com.brunocelestino.agendamento_barbearia.data.repository.ServicoRepository;
import br.com.brunocelestino.agendamento_barbearia.dto.AgendamentoDto;
import br.com.brunocelestino.agendamento_barbearia.dto.AgendamentoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;
    private final BarbeiroRepository barbeiroRepository;
    private final ServicoRepository servicoRepository;

    public Page<AgendamentoResponseDto> findAllAgendamentos(Pageable page) {
        Page<Agendamento> agendamentos = agendamentoRepository.findAll(page);
        return agendamentoReponseDtoBuilder(agendamentos);
    }

    public Page<AgendamentoResponseDto> findAgendamentosByCliente(UUID clienteId, Pageable page) {
        Page<Agendamento> agendamentos = agendamentoRepository.findAllByCliente(clienteId, page);
        return agendamentoReponseDtoBuilder(agendamentos);
    }

    public void createAgendamento(AgendamentoDto agendamentoDto){

        Cliente cliente = clienteRepository.findById(agendamentoDto.getClienteId()).orElseThrow();
        Barbeiro barbeiro = barbeiroRepository.findById(agendamentoDto.getBarbeiroId()).orElseThrow();
        Servico servico = servicoRepository.findById(agendamentoDto.getServicoId()).orElseThrow();

        if (agendamentoDto.getDataHoraInicio().isBefore(LocalDateTime.now()))
            throw new RuntimeException();

        if (agendamentoDto.getDataHoraInicio() == null)
            throw new RuntimeException();

        if (agendamentoDto.getObservacao().isBlank())
            agendamentoDto.setObservacao("Nenhuma observação.");

        LocalDateTime dataHoraFim = agendamentoDto.getDataHoraInicio().plusMinutes(servico.getDuracaoEmMinutos());


        agendamentoRepository.save(
                new Agendamento(
                        null,
                        agendamentoDto.getDataHoraInicio(),
                        dataHoraFim,
                        StatusAgendamento.AGENDADO,
                        agendamentoDto.getObservacao(),
                        cliente,
                        barbeiro,
                        servico,
                        agendamentoDto.getPrecoCobrado()
                ));

    }

    public void confirmAgendamento(UUID agendamentoId) {
        Agendamento agendamento = agendamentoRepository.findById(agendamentoId).orElseThrow();

        if (agendamento.getStatus().equals(StatusAgendamento.CONFIRMADO))
            throw new RuntimeException("Esse agendamento já está confirmado!");

        if (agendamento.getStatus().equals(StatusAgendamento.CONCLUIDO))
            throw new RuntimeException("Esse agendamento já foi concluído.");

        if (agendamento.getStatus().equals(StatusAgendamento.CANCELADO))
            throw new RuntimeException("Esse agendamento foi cancelado. É necessário realizar outro agendamento.");

        if (agendamento.getStatus().equals(StatusAgendamento.NAO_COMPARECEU))
            throw new RuntimeException("O cliente não compareceu ao agendamento.");


        agendamento.confirmAgendamento();
        agendamentoRepository.save(agendamento);

    }

    public void cancelAgendamento(UUID agendamentoId){
        Agendamento agendamento = agendamentoRepository.findById(agendamentoId).orElseThrow();

        if (agendamento.getDataHoraInicio().isAfter(LocalDateTime.now().minusHours(2)))
            throw new RuntimeException("Só é possível fazer cancelamento com 2 horas de antecedência.");

        if (agendamento.getDataHoraInicio().isBefore(LocalDateTime.now().minusHours(2))){
            if (agendamento.getStatus().equals(StatusAgendamento.CONCLUIDO))
                throw new RuntimeException("Esse agendamento já foi concluído.");

            if (agendamento.getStatus().equals(StatusAgendamento.CANCELADO))
                throw new RuntimeException("Esse agendamento já está cancelado.");

            if (agendamento.getStatus().equals(StatusAgendamento.NAO_COMPARECEU))
                throw new RuntimeException("O cliente não compareceu ao agendamento. Não é necessário cancelar!");
            agendamento.cancelAgendamento();
            agendamentoRepository.save(agendamento);
        }
    }

    private Page<AgendamentoResponseDto> agendamentoReponseDtoBuilder(Page<Agendamento> agendamentos){
        return agendamentos.map(agendamento ->
                new AgendamentoResponseDto(
                        agendamento.getCliente().getNome(),
                        agendamento.getBarbeiro().getNome(),
                        agendamento.getServico().getNome(),
                        agendamento.getDataHoraInicio(),
                        agendamento.getDataHoraFim(),
                        agendamento.getObservacao(),
                        agendamento.getPrecoCobrado()));
    }
}
