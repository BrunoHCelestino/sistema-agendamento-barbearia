package br.com.brunocelestino.agendamento_barbearia.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ServicoDto (

        @NotBlank(message = "O nome não pode estar em branco")
        String nome,

        @NotBlank(message = "A descrição não pode estar em branco")
        String descricao,

        @NotNull(message = "O preço não pode ser nulo.")
        @Min(value = 1, message = "O preço mínimo é 1.")
        BigDecimal preco,

        @NotNull(message = "A duração não pode ser nula;")
        @Min(value = 10, message = "A duração mínima é de 10 minutos.")
        Integer duracaoEmMinutos
){}