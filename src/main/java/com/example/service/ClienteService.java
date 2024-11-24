package com.example.service;

import com.example.entity.Cliente;
import com.example.repository.ClienteRepository;

import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@Singleton
@AllArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar(Cliente cliente) {
    if (cliente.getId() != null && clienteRepository.findById(cliente.getId()).isPresent()) {
        return clienteRepository.update(cliente);
    }
        return clienteRepository.save(cliente);
}

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Transactional
    public Cliente atualizar(Long id, Cliente clienteAtualizado) {
        Cliente clienteExistente = clienteRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));
    
        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setEmail(clienteAtualizado.getEmail());
        clienteExistente.setTelefone(clienteAtualizado.getTelefone());
    
        return clienteRepository.update(clienteExistente);
    }
    

    @Transactional
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}
