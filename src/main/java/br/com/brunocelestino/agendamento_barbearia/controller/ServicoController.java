package br.com.brunocelestino.agendamento_barbearia.controller;

import br.com.brunocelestino.agendamento_barbearia.data.entity.Servico;
import br.com.brunocelestino.agendamento_barbearia.dto.ServicoDto;
import br.com.brunocelestino.agendamento_barbearia.dto.ServicoUpdateDto;
import br.com.brunocelestino.agendamento_barbearia.service.ServicoService;
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
@RequiredArgsConstructor
@RequestMapping("/v1/servico")
public class ServicoController {

    private final ServicoService servicoService;

    @GetMapping("/find-all")
    public ResponseEntity<Page<Servico>> findAllServicos (@PageableDefault(
            sort = "id",
            direction = Sort.Direction.ASC)
            Pageable page){
        return ResponseEntity.ok(servicoService.findAllServicos(page));
    }

    @GetMapping("/find-by-id/{servicoId}")
    public ResponseEntity<Servico> findById(@PathVariable UUID servicoId){
        return ResponseEntity.ok(servicoService.findById(servicoId));
    }

    @GetMapping("/find-all-activated")
    public ResponseEntity<Page<Servico>> findAllServicosActivated (@PageableDefault(
            sort = "id",
            direction = Sort.Direction.ASC)
                                                              Pageable page){
        return ResponseEntity.ok(servicoService.findAllServicosActivated(page));
    }

    @PostMapping("/create-servico")
    public ResponseEntity<Void> createServico(@RequestBody @Valid ServicoDto servicoDto){
        servicoService.createServico(servicoDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/toggle-servico-status/{id}")
    public ResponseEntity<Void> alternarStatusAtivo (@PathVariable UUID id){
        servicoService.toggleServicoStatus(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PatchMapping("/update-servico/{id}")
    public ResponseEntity<ServicoDto> updateServico(@PathVariable UUID id,@RequestBody ServicoUpdateDto servicoUpdateDto){
        return ResponseEntity.ok(servicoService.updateServico(id, servicoUpdateDto));
    }

}
