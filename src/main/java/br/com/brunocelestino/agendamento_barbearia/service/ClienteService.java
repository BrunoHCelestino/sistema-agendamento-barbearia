package br.com.brunocelestino.agendamento_barbearia.service;

import br.com.brunocelestino.agendamento_barbearia.data.entity.Cliente;
import br.com.brunocelestino.agendamento_barbearia.data.repository.ClienteRepository;
import br.com.brunocelestino.agendamento_barbearia.dto.ClienteDto;
import br.com.brunocelestino.agendamento_barbearia.dto.ClienteUpdateDto;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[\\w.%+-]+@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*\\.[A-Za-z]{2,}$");

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
    public ClienteDto updateCliente(ClienteUpdateDto clienteDto){
        Cliente cliente = clienteRepository.findByEmail(clienteDto.emailAntigo()).orElseThrow();

        if (clienteDto.email() != null){
            if (!emailValido(clienteDto.email())){
                throw new IllegalArgumentException("Email inválido");
            }
            cliente.setEmail(clienteDto.email());
        }

        if (clienteDto.telefone() != null)
            if (clienteDto.telefone().length() >= 11 && clienteDto.telefone().length() <= 15)
                cliente.setTelefone(clienteDto.telefone());

        clienteRepository.save(cliente);

        return ClienteDto.builder()
                .nome(cliente.getNome())
                .telefone(cliente.getTelefone())
                .email(cliente.getEmail())
                .build();
    }

    @Transactional
    public void alternarStatusCliente(String email){
        if(clienteRepository.existsByEmail(email))
            clienteRepository.toggleStatus(email);
    }

    private boolean emailValido(String email) {
        return email != null && EMAIL_PATTERN.matcher(email.trim()).matches();
    }

}
