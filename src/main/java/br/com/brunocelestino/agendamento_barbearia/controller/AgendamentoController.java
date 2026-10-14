package br.com.brunocelestino.agendamento_barbearia.controller;

import br.com.brunocelestino.agendamento_barbearia.data.enums.StatusAgendamento;
import br.com.brunocelestino.agendamento_barbearia.dto.AgendamentoDto;
import br.com.brunocelestino.agendamento_barbearia.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/agendamento")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping("/create-agendamento")
    public void createAgendamento(@RequestBody AgendamentoDto agendamentoDto){
        agendamentoService.createAgendamento(agendamentoDto);
    }

    @PatchMapping("/confirm-agendamento/{agendamentoId}")
    public void confirmAgendamento(@PathVariable UUID agendamentoId){
        agendamentoService.confirmAgendamento(agendamentoId);
    }

    @PatchMapping("/cancel-agendamento/{agendamentoId}")
    public void cancelAgendamento(@PathVariable UUID agendamentoId){
        agendamentoService.cancelAgendamento(agendamentoId);
    }

}
