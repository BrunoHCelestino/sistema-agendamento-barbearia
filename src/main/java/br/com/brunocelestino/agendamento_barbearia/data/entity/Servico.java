package br.com.brunocelestino.agendamento_barbearia.data.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "O nome não pode estar em branco.")
    private String nome;

    @NotBlank(message = "A descrição não pode estar em branco.")
    private String descricao;

    @NotNull(message = "O preço não pode ser nulo.")
    @Min(value = 1, message = "O preço mínimo é 1.")
    private BigDecimal preco;

    @NotNull(message = "A duração não pode ser nula;")
    @Min(value = 10, message = "A duração mínima é de 10 minutos.")
    private Integer duracaoEmMinutos;

    private boolean ativo;

}
