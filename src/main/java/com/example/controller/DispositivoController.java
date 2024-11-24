package com.example.controller;

import com.example.entity.Dispositivo;
import com.example.repository.DispositivoRepository;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller("/dispositivos")
@AllArgsConstructor
public class DispositivoController {
    private final DispositivoRepository dispositivoRepository;

    @Post
    @Transactional
    public HttpResponse<Dispositivo> cadastrar(@Body @Valid Dispositivo dispositivo) {
        return HttpResponse.created(dispositivoRepository.save(dispositivo));
    }

    @Get("/{id}")
    public Dispositivo detalhar(@PathVariable Long id) {
        return dispositivoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Dispositivo não encontrado"));
    }

    @Delete("/{id}")
    @Transactional
    public HttpResponse<Void> deletar(@PathVariable Long id) {
        dispositivoRepository.deleteById(id);
        return HttpResponse.noContent();
    }
}

