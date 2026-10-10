package br.com.brunocelestino.agendamento_barbearia.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "servicos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    @NotBlank(message = "A descrição não pode estar em branco")
    private String descricao;

    @NotBlank(message = "O preço não pode estar em branco")
    @Min(1)
    private BigDecimal preco;

    @NotBlank(message = "O a duração não pode estar em branco")
    @Min(1)
    private Integer duracaoEmMinutos;

    private boolean ativo;

}
