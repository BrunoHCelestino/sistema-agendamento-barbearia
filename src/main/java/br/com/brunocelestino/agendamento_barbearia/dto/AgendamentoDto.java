package br.com.brunocelestino.agendamento_barbearia.dto;

import br.com.brunocelestino.agendamento_barbearia.data.entity.Barbeiro;
import br.com.brunocelestino.agendamento_barbearia.data.entity.Cliente;
import br.com.brunocelestino.agendamento_barbearia.data.entity.Servico;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
@Builder
@Setter
public class AgendamentoDto {

    private LocalDateTime dataHoraInicio;

    private UUID clienteId;

    private UUID barbeiroId;

    private UUID servicoId;

    private String observacao;

    private BigDecimal precoCobrado;

}
