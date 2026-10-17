package br.com.brunocelestino.agendamento_barbearia.controller;

import br.com.brunocelestino.agendamento_barbearia.data.entity.Agendamento;
import br.com.brunocelestino.agendamento_barbearia.dto.AgendamentoDto;
import br.com.brunocelestino.agendamento_barbearia.dto.AgendamentoResponseDto;
import br.com.brunocelestino.agendamento_barbearia.service.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/agendamento")
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @GetMapping("/find-all-agendamentos")
    public ResponseEntity<Page<AgendamentoResponseDto>> findAllAgendamentos(@PageableDefault(
            sort = "id",
            direction = Sort.Direction.ASC)
            Pageable page){
        return ResponseEntity.ok(agendamentoService.findAllAgendamentos(page));
    }

    @GetMapping("/find-agendamentos-by-cliente/{clienteId}")
    public ResponseEntity<Page<AgendamentoResponseDto>> findAgendamentosByCliente(@PathVariable UUID clienteId,
            @PageableDefault(
            sort = "id",
            direction = Sort.Direction.ASC)
            Pageable page){
        return ResponseEntity.ok(agendamentoService.findAgendamentosByCliente(clienteId, page));
    }

    @PostMapping("/create-agendamento")
    public ResponseEntity<Void> createAgendamento(@RequestBody AgendamentoDto agendamentoDto){
        agendamentoService.createAgendamento(agendamentoDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/confirm-agendamento/{agendamentoId}")
    public ResponseEntity<Void> confirmAgendamento(@PathVariable UUID agendamentoId){
        agendamentoService.confirmAgendamento(agendamentoId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/cancel-agendamento/{agendamentoId}")
    public ResponseEntity<Void> cancelAgendamento(@PathVariable UUID agendamentoId){
        agendamentoService.cancelAgendamento(agendamentoId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
