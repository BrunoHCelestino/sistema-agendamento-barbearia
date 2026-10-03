package br.com.brunocelestino.agendamento_barbearia.controller;

import br.com.brunocelestino.agendamento_barbearia.data.entity.Cliente;
import br.com.brunocelestino.agendamento_barbearia.dto.ClienteDto;
import br.com.brunocelestino.agendamento_barbearia.service.ClienteService;
import jakarta.validation.Valid;
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
@RequestMapping("/v1/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping("/find-all")
    public ResponseEntity<Page<Cliente>> findAllClientes(@PageableDefault(
            sort = "id",
            direction = Sort.Direction.ASC)
            Pageable page){
        return ResponseEntity.ok(clienteService.findAllClientes(page));
    }

    @GetMapping("/find-all-activated")
    public ResponseEntity<Page<Cliente>> findAllClientesActivated(@PageableDefault(
            sort = "id",
            direction = Sort.Direction.ASC)
                                                         Pageable page){
        return ResponseEntity.ok(clienteService.findAllClientesActivated(page));
    }

    @GetMapping("/find-by-id/{clienteId}")
    public ResponseEntity<Cliente> findByClienteById(@PathVariable UUID clienteId){
        return ResponseEntity.ok(clienteService.findByClienteId(clienteId));
    }

    @PostMapping("/create-cliente")
    public ResponseEntity<Void> createCliente(@Valid @RequestBody ClienteDto cliente){
        clienteService.createCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/alternar-status-cliente/{email}")
    public ResponseEntity<Void> alternarStatusAtivo(@PathVariable String email){
        clienteService.alternarStatusCliente(email);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
