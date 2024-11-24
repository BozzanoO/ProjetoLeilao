package com.example.service;

import com.example.entity.EntidadeFinanceira;
import com.example.repository.EntidadeFinanceiraRepository;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@Singleton
public class EntidadeFinanceiraService {

    private final EntidadeFinanceiraRepository entidadeFinanceiraRepository;

    @Transactional
    public EntidadeFinanceira salvar(EntidadeFinanceira entidade) {
        return entidadeFinanceiraRepository.save(entidade);
    }

    public List<EntidadeFinanceira> listarTodos() {
        return entidadeFinanceiraRepository.findAll();
    }

    public Optional<EntidadeFinanceira> buscarPorId(Long id) {
        return entidadeFinanceiraRepository.findById(id);
    }

    @Transactional
    public EntidadeFinanceira atualizar(Long id, EntidadeFinanceira entidadeAtualizada) {
        EntidadeFinanceira Entidade = entidadeFinanceiraRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Instituição não encontrada."));
        Entidade.setNome(entidadeAtualizada.getNome());
        Entidade.setCnpj(entidadeAtualizada.getCnpj());
        return entidadeFinanceiraRepository.save(Entidade);
    }

    @Transactional
    public void deletar(Long id) {
        entidadeFinanceiraRepository.deleteById(id);
    }
}
