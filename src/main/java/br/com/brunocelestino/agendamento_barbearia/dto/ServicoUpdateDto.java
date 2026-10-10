package br.com.brunocelestino.agendamento_barbearia.dto;

import java.math.BigDecimal;

public record ServicoUpdateDto(String nome,

                               String descricao,

                               BigDecimal preco,

                               Integer duracaoEmMinutos) {
}
