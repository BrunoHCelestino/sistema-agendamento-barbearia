package br.com.brunocelestino.agendamento_barbearia.service;

import br.com.brunocelestino.agendamento_barbearia.data.entity.Cliente;
import br.com.brunocelestino.agendamento_barbearia.data.repository.ClienteRepository;
import br.com.brunocelestino.agendamento_barbearia.dto.ClienteDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public Page<Cliente> findAllClientes(Pageable page){
        return clienteRepository.findAll(page);
    }

    public Page<Cliente> findAllClientesActivated(Pageable page){
        return clienteRepository.findAllActivated(page);
    }
    public Cliente findByClienteId(UUID id){
        return clienteRepository.findById(id)
                .orElseThrow();
    }

    public void createCliente(ClienteDto cliente){
        clienteRepository.save(new Cliente(
                null,
                cliente.nome(),
                cliente.telefone(),
                cliente.email(),
                true
        ));
    }

    @Transactional
    public void alternarStatusCliente(String email){
        if(clienteRepository.existsByEmail(email))
            clienteRepository.toggleStatus(email);
    }

}
