package br.com.brunocelestino.agendamento_barbearia.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Dados para atualização de cliente")
public record ClienteUpdateDto(

        @NotBlank(message = "O e-mail antigo não pode estar vazio.")
        @Email
        String emailAntigo,

        String telefone,

        String email
){}
