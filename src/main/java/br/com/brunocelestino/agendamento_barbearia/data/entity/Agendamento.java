package br.com.brunocelestino.agendamento_barbearia.data.entity;

import br.com.brunocelestino.agendamento_barbearia.data.enums.StatusAgendamento;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "agendamentos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;

    @Enumerated(EnumType.STRING)
    private StatusAgendamento status;

    private String observacao;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Barbeiro barbeiro;

    @ManyToOne
    private Servico servico;

    private BigDecimal precoCobrado;

    public void confirmAgendamento(){
        this.status = StatusAgendamento.CONFIRMADO;
    }

    public void cancelAgendamento(){
        this.status = StatusAgendamento.CANCELADO;
    }

}
