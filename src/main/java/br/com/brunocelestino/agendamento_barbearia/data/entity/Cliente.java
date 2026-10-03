package br.com.brunocelestino.agendamento_barbearia.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @NotBlank(message = "O nome não pode estar vazio.")
    private String nome;

    @NotBlank(message = "O telefone não pode estar vazio.")
    @Column(length = 15)
    private String telefone;

    @NotBlank(message = "O e-mail não pode estar vazio.")
    @Column(unique = true)
    private String email;

    private boolean ativo = true;

}
