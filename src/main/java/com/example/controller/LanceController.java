package com.example.controller;

import java.util.List;

import com.example.entity.Lance;
import com.example.repository.LanceRepository;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller("/lances")
@AllArgsConstructor
public class LanceController {
    private final LanceRepository lanceRepository;

    @Post
    @Transactional
    public HttpResponse<Lance> registrar(@Body @Valid Lance lance) {
        return HttpResponse.created(lanceRepository.save(lance));
    }

    @Get("/{produtoId}")
    public List<Lance> listarPorProduto(@PathVariable Long produtoId) {
        return lanceRepository.findByProdutoId(produtoId);
    }
}

