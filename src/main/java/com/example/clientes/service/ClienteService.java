package com.example.clientes.service;

import com.example.clientes.domain.Cliente;
import com.example.clientes.dto.request.ClienteRequestDTO;
import com.example.clientes.exception.CpfExistenteException;
import com.example.clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;


    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> buscarTodos(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id){
        return clienteRepository.findById(id);
    }

    public Cliente salvar(ClienteRequestDTO dto){
        // Regra de Negócio: Verificar se o CPF já existe
        Optional<Cliente> clienteExistente = clienteRepository.findByCpf(dto.getCpf());
        if (clienteExistente.isPresent()) {
            throw new CpfExistenteException("O CPF informado já está cadastrado.");
        }

        // Mapeamento do DTO para a entidade Cliente
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setCpf(dto.getNome());
        cliente.setCpf(dto.getCpf());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEndereco(dto.getEndereco());

        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Long id, ClienteRequestDTO dto) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isPresent()){
            Cliente clienteParaAtualizar = clienteExistente.get();

            // Regra de Negócio: Verificar se o CPF já existe em outro cliente
            Optional<Cliente> clienteCpfDuplicado = clienteRepository.findByCpf(dto.getCpf());
            if (clienteCpfDuplicado.isPresent() && !clienteCpfDuplicado.get().getId().equals(clienteParaAtualizar.getId())) {
                throw new CpfExistenteException("O CPF Informado já está cadastrado em outro cliente.");
            }
            // Mapeamento dos dados do DTO para a entidade existente
            clienteParaAtualizar.setNome(dto.getNome());
            clienteParaAtualizar.setCpf(dto.getCpf());
            clienteParaAtualizar.setEmail(dto.getEmail());
            clienteParaAtualizar.setTelefone(dto.getTelefone());

            // Mapeamento e atualização do endereço
            clienteParaAtualizar.getEndereco().setLogradouro((dto.getEndereco().getLogradouro()));
            clienteParaAtualizar.getEndereco().setNumero(dto.getEndereco().getNumero());
            clienteParaAtualizar.getEndereco().setCep(dto.getEndereco().getCep());
            clienteParaAtualizar.getEndereco().setCidade(dto.getEndereco().getCidade());
            clienteParaAtualizar.getEndereco().setEstado(dto.getEndereco().getEstado());

            return clienteRepository.save(clienteParaAtualizar);
        } else {
            throw new IllegalStateException("Cliente não encontrado para o ID: " + id);
        }
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}
