package com.example.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import com.example.entity.Leilao;
import com.example.entity.Produto;
import com.example.entity.enums.StatusLeilao;
import com.example.repository.LeilaoRepository;
import com.example.repository.ProdutoRepository;
import com.example.service.ExportacaoService;

import io.micronaut.data.model.Sort;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.annotation.Scheduled;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller("/leiloes")
@AllArgsConstructor
public class LeilaoController {

    private final LeilaoRepository leilaoRepository;
    private final ProdutoRepository produtoRepository;
    private final ExportacaoService exportacaoService;

    @Post
    @Transactional
    public HttpResponse<Leilao> cadastrar(@Body @Valid Leilao leilao) {
        return HttpResponse.created(leilaoRepository.save(leilao));
    }

    @Get
    public List<Leilao> listar() {
        return leilaoRepository.findAll(Sort.of(Sort.Order.asc("dataInicio")));
    }

    @Get("/{id}")
    public Leilao detalhar(@PathVariable Long id) {
        return leilaoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Leilão não encontrado"));
    }

    @Post("/{id}/associar")
    @Transactional
    public HttpResponse<Produto> associarProduto(@PathVariable Long id, @Body @Valid ProdutoAssociacao request) {
        Leilao leilao = leilaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Leilão não encontrado"));

        Produto produto = produtoRepository.findById(request.getProdutoId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));

        if (!leilao.getDataInicio().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Não é possível associar produtos a leilões passados.");
        }

        produto.setLeilao(leilao);
        produtoRepository.save(produto);

        return HttpResponse.ok(produto);
    }

    @Scheduled(fixedRate = "1m")
    @Transactional
    public void atualizarStatusLeiloes() {
        List<Leilao> leiloes = leilaoRepository.findAll();

        for (Leilao leilao : leiloes) {
            if (LocalDateTime.now().isBefore(leilao.getDataInicio())) {
                leilao.setStatus(StatusLeilao.EM_ABERTO);
            } else if (LocalDateTime.now().isAfter(leilao.getDataFim())) {
                leilao.setStatus(StatusLeilao.FINALIZADO);
            } else {
                leilao.setStatus(StatusLeilao.EM_ANDAMENTO);
            }
            leilaoRepository.update(leilao);
        }
    }

    @Post("/{id}/exportar")
    public void exportarLeilao(@PathVariable Long id, @Body ExportacaoRequest request) {
        Leilao leilao = leilaoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Leilão não encontrado"));

        try {
            exportacaoService.exportarLeilaoParaArquivo(leilao, request.getCaminhoArquivo());
        } catch (IOException e) {
            throw new RuntimeException("Erro ao exportar o arquivo: " + e.getMessage());
        }
    }
}
