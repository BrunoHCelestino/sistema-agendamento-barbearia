package br.com.brunocelestino.agendamento_barbearia.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClienteDto(

        @NotBlank(message = "O nome não pode estar vazio.")
        String nome,
        @NotBlank(message = "O telefone não pode estar vazio.")
        @Pattern(regexp = "^\\d{10,11}$", message = "Telefone deve ter 10 ou 11 dígitos (DDD + número)")
        @Size(min = 11, max = 15)
        String telefone,
        @NotBlank(message = "O e-mail não pode estar vazio.")
        @Email
        String email
){}
