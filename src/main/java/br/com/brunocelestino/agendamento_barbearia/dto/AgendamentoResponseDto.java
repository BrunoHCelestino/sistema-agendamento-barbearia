package br.com.brunocelestino.agendamento_barbearia.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AgendamentoResponseDto {

    private String cliente;
    private String barbeiro;
    private String servico;

    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;

    private String observacao;
    private BigDecimal precoCobrado;

}
