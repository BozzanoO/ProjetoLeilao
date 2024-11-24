package com.example.controller;

import com.example.entity.Veiculo;
import com.example.repository.VeiculoRepository;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller("/veiculos")
@AllArgsConstructor
public class VeiculoController {
    private final VeiculoRepository veiculoRepository;

    @Post
    @Transactional
    public HttpResponse<Veiculo> cadastrar(@Body @Valid Veiculo veiculo) {
        return HttpResponse.created(veiculoRepository.save(veiculo));
    }

    @Get("/{id}")
    public Veiculo detalhar(@PathVariable Long id) {
        return veiculoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Veiculo não encontrado"));
    }

    @Delete("/{id}")
    @Transactional
    public HttpResponse<Void> deletar(@PathVariable Long id) {
        veiculoRepository.deleteById(id);
        return HttpResponse.noContent();
    }
}
