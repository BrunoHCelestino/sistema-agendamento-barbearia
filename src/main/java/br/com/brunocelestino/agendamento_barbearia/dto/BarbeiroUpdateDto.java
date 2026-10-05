package br.com.brunocelestino.agendamento_barbearia.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record BarbeiroUpdateDto(
        @NotBlank(message = "O telefone antigo não pode estar vazio.")
        @Pattern(regexp = "^\\d{10,11}$", message = "Telefone deve ter 10 ou 11 dígitos (DDD + número)")
        String telefoneAntigo,

        @NotBlank(message = "O telefone não pode estar vazio.")
        @Pattern(regexp = "^\\d{10,11}$", message = "Telefone deve ter 10 ou 11 dígitos (DDD + número)")
        String telefone
) {}
