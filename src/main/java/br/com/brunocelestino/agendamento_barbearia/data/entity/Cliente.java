package br.com.brunocelestino.agendamento_barbearia.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O nome não pode estar vazio.")
    private String nome;

    @NotBlank(message = "O telefone não pode estar vazio.")
    @Column(length = 15)
    @Pattern(regexp = "^\\d{10,11}$", message = "Telefone deve ter 10 ou 11 dígitos (DDD + número)")
    private String telefone;

    @NotBlank(message = "O e-mail não pode estar vazio.")
    @Column(unique = true)
    @Email
    private String email;

    private boolean ativo;

}
