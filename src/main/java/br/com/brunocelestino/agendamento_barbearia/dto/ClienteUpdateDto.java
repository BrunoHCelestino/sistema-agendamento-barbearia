package br.com.brunocelestino.agendamento_barbearia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteUpdateDto(

        @NotBlank(message = "O e-mail antigo não pode estar vazio.")
        @Email
        String emailAntigo,

        String telefone,

        String email
){}
