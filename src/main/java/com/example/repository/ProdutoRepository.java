package com.example.repository;

import java.util.List;

import com.example.entity.Produto;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
   List<Produto> findByLeilaoId(Long leilaoId);
}

