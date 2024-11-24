package com.example.controller;

import com.example.entity.EntidadeFinanceira;
import com.example.service.EntidadeFinanceiraService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller("/entidade-financeira")
public class EntidadeFinanceiraController {

    private final EntidadeFinanceiraService entidadeFinanceiraService;

    @Post(consumes = MediaType.APPLICATION_JSON, produces = MediaType.APPLICATION_JSON)
    public HttpResponse<EntidadeFinanceira> salvar(@Body EntidadeFinanceira entidadeFinanceira) {
        EntidadeFinanceira novaEntidade = entidadeFinanceiraService.salvar(entidadeFinanceira);
        return HttpResponse.created(novaEntidade);
    }

    @Get(produces = MediaType.APPLICATION_JSON)
    public List<EntidadeFinanceira> listarTodos() {
        return entidadeFinanceiraService.listarTodos();
    }

    @Get("/{id}")
    public HttpResponse<EntidadeFinanceira> buscarPorId(@PathVariable Long id) {
        Optional<EntidadeFinanceira> entidade = entidadeFinanceiraService.buscarPorId(id);
        return entidade.map(HttpResponse::ok).orElseGet(() -> HttpResponse.notFound());
    }

    @Put("/{id}")
    public HttpResponse<EntidadeFinanceira> atualizar(@PathVariable Long id, @Body EntidadeFinanceira entidadeAtualizada) {
        try {
            EntidadeFinanceira entidade = entidadeFinanceiraService.atualizar(id, entidadeAtualizada);
            return HttpResponse.ok(entidade);
        } catch (IllegalArgumentException e) {
            return HttpResponse.notFound();
        }
    }

    @Delete("/{id}")
    public HttpResponse<Void> deletar(@PathVariable Long id) {
        try {
            entidadeFinanceiraService.deletar(id);
            return HttpResponse.noContent();
        } catch (IllegalArgumentException e) {
            return HttpResponse.notFound();
        }
    }
}
