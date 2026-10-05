package br.com.brunocelestino.agendamento_barbearia.controller;

import br.com.brunocelestino.agendamento_barbearia.service.BarbeiroService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/barbeiro")
@RequiredArgsConstructor
public class BarbeiroController {

    private final BarbeiroService barbeiroService;

}
