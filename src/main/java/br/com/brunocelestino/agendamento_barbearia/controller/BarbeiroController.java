package br.com.brunocelestino.agendamento_barbearia.controller;

import br.com.brunocelestino.agendamento_barbearia.data.entity.Barbeiro;
import br.com.brunocelestino.agendamento_barbearia.dto.BarbeiroDto;
import br.com.brunocelestino.agendamento_barbearia.dto.BarbeiroUpdateDto;
import br.com.brunocelestino.agendamento_barbearia.service.BarbeiroService;
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
@RequestMapping("/v1/barbeiro")
@RequiredArgsConstructor
public class BarbeiroController {

    private final BarbeiroService barbeiroService;

    @GetMapping("/find-all")
    public ResponseEntity<Page<Barbeiro>> findAllBarbeiros(@PageableDefault(
            sort = "id",
            direction = Sort.Direction.ASC)
            Pageable page){
        return ResponseEntity.ok(barbeiroService.findAllBarbeiros(page));
    }

    @GetMapping("/find-all-activated")
    public ResponseEntity<Page<Barbeiro>> findAllBarbeirosActivated(@PageableDefault(
            sort = "id",
            direction = Sort.Direction.ASC)
            Pageable page){
        return ResponseEntity.ok(barbeiroService.findAllBarbeirosActivated(page));
    }

    @GetMapping("/find-by-id/{barbeiroId}")
    public ResponseEntity<Barbeiro> findById(@PathVariable UUID barbeiroId){
        return ResponseEntity.ok(barbeiroService.findById(barbeiroId));
    }

    @PostMapping("/create-barbeiro")
    public ResponseEntity<Void> createBarbeiro(@Valid @RequestBody BarbeiroDto barbeiroDto){
        barbeiroService.createBarbeiro(barbeiroDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/update-barbeiro")
    public ResponseEntity<BarbeiroDto> updateBarbeiro(@Valid @RequestBody BarbeiroUpdateDto barbeiroUpdateDto){
        return ResponseEntity.ok(barbeiroService.updateBarbeiro(barbeiroUpdateDto));
    }

    @PatchMapping("/toggle-barbeiro-status/{barbeiroId}")
    public ResponseEntity<Void> toggleBarbeiroStatus(@PathVariable UUID barbeiroId){
        barbeiroService.toggleBarbeiroStatus(barbeiroId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
