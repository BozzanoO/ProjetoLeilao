package com.example.service;

import com.example.entity.Leilao;
import com.example.repository.LeilaoRepository;

import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@Singleton
@AllArgsConstructor
public class LeilaoService {

    private final LeilaoRepository leilaoRepository;

    @Transactional
    public Leilao salvar(Leilao leilao) {
        return leilaoRepository.save(leilao);
    }

    public List<Leilao> listarTodos() {
        return leilaoRepository.findAll();
    }

    public Optional<Leilao> buscarPorId(Long id) {
        return leilaoRepository.findById(id);
    }

    @Transactional
    public Leilao atualizar(Long id, Leilao leilaoAtualizado) {
        Leilao leilao = leilaoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Leilão não encontrado."));
        leilao.setNome(leilaoAtualizado.getNome());
        leilao.setDataInicio(leilaoAtualizado.getDataInicio());
        leilao.setLocal(leilaoAtualizado.getLocal());
        return leilaoRepository.save(leilao);
    }

    @Transactional
    public void deletar(Long id) {
        leilaoRepository.deleteById(id);
    }
}

