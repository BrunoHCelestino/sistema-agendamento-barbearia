package br.com.brunocelestino.agendamento_barbearia.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgendamentoDto {

    private LocalDateTime dataHoraInicio;

    private UUID clienteId;

    private UUID barbeiroId;

    private UUID servicoId;

    private String observacao;

    private BigDecimal precoCobrado;

}
