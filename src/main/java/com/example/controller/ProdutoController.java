package com.example.controller;

import com.example.entity.Produto;
import com.example.repository.ProdutoRepository;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Controller("/produtos")
@AllArgsConstructor
public class ProdutoController {
   private final ProdutoRepository produtoRepository;

   @Post
   @Transactional
   public HttpResponse<Produto> cadastrar(@Body @Valid Produto produto) {
       Produto novoProduto = produtoRepository.save(produto);
       return HttpResponse.created(novoProduto);
   }

   @Get("/{id}")
   public Produto detalhar(@PathVariable Long id) {
       return produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
   }

}

