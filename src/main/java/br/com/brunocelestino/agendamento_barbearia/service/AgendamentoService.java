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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;
    private final BarbeiroRepository barbeiroRepository;
    private final ServicoRepository servicoRepository;

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

}
