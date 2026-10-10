package br.com.brunocelestino.agendamento_barbearia.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ServicoDto (

        @NotBlank(message = "O nome não pode estar em branco")
        String nome,

        @NotBlank(message = "A descrição não pode estar em branco")
        String descricao,

        @NotBlank(message = "O preço não pode estar em branco")
        @Min(1)
        BigDecimal preco,

        @NotBlank(message = "O a duração não pode estar em branco")
        @Min(1)
        Integer duracaoEmMinutos
){}
