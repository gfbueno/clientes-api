package com.example.clientes.controller;

import com.example.clientes.domain.Cliente;
import com.example.clientes.dto.request.ClienteRequestDTO;
import com.example.clientes.dto.response.ClienteResponseDTO;
import com.example.clientes.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> buscarTodos() {
        List<Cliente> clientes = clienteService.buscarTodos();
        List<ClienteResponseDTO> dtos = clientes.stream()
                .map(ClienteResponseDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.buscarPorId(id);
        return cliente.map(c -> ResponseEntity.ok(new ClienteResponseDTO(c)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> salvar(@Valid @RequestBody ClienteRequestDTO dto) {
        Cliente novoCliente = clienteService.salvar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ClienteResponseDTO((novoCliente)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> Atualizar(@PathVariable Long id, @Valid @RequestBody ClienteRequestDTO dto) {
        Cliente clienteAtualizado = clienteService.atualizar(id, dto);
        return ResponseEntity.ok(new ClienteResponseDTO(clienteAtualizado));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Optional<Cliente> clienteExistente = clienteService.buscarPorId(id);
        if (clienteExistente.isPresent()) {
            clienteService.deletar(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}