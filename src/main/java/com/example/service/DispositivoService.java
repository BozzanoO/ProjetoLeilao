package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.entity.Dispositivo;
import com.example.repository.DispositivoRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DispositivoService {
    
    private final DispositivoRepository dispositivoRepository;

    @Transactional
    public Dispositivo salvar(Dispositivo dispositivo) {
        return dispositivoRepository.save(dispositivo);
    }

    public List<Dispositivo> listarTodos() {
        return dispositivoRepository.findAll();
    }

    public Optional<Dispositivo> buscarPorId(Long id) {
        return dispositivoRepository.findById(id);
    }

    @Transactional
    public Dispositivo atualizar(Long id, Dispositivo dispositivoAtualizado) {
        Dispositivo dispositivo = dispositivoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Veículo não encontrado."));
        dispositivo.setEspecificacoesTecnicas(dispositivoAtualizado.getEspecificacoesTecnicas());
        dispositivo.setTipo(dispositivoAtualizado.getTipo());
        dispositivo.setValorInicial(dispositivoAtualizado.getValorInicial());
        dispositivo.setLeilao(dispositivoAtualizado.getLeilao());
        return dispositivoRepository.save(dispositivo);
    }

    @Transactional
    public void deletar(Long id) {
        dispositivoRepository.deleteById(id);
    }
}
