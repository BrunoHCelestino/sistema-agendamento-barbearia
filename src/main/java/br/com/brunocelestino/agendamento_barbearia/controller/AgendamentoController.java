package br.com.brunocelestino.agendamento_barbearia.controller;

import br.com.brunocelestino.agendamento_barbearia.dto.AgendamentoDto;
import br.com.brunocelestino.agendamento_barbearia.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/agendamento")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping("/create-agendamento")
    public void createAgendamento(@RequestBody AgendamentoDto agendamentoDto){
        agendamentoService.createAgendamento(agendamentoDto);
    }

}
