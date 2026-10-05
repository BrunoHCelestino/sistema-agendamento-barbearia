package br.com.brunocelestino.agendamento_barbearia.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "barbeiros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Barbeiro {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;
    private String telefone;
    private boolean ativo;


}
