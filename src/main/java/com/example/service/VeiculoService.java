package com.example.service;

import com.example.entity.Veiculo;
import com.example.repository.VeiculoRepository;

import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@Singleton
@AllArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;

    @Transactional
    public Veiculo salvar(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> buscarPorId(Long id) {
        return veiculoRepository.findById(id);
    }

    @Transactional
    public Veiculo atualizar(Long id, Veiculo veiculoAtualizado) {
        Veiculo veiculo = veiculoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado."));
        veiculo.setModelo(veiculoAtualizado.getModelo());
        veiculo.setMarca(veiculoAtualizado.getMarca());
        veiculo.setValorInicial(veiculoAtualizado.getValorInicial());
        veiculo.setLeilao(veiculoAtualizado.getLeilao());
        return veiculoRepository.save(veiculo);
    }

    @Transactional
    public void deletar(Long id) {
        veiculoRepository.deleteById(id);
    }
}

