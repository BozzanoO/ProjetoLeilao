package com.example.repository;

import java.util.List;

import com.example.entity.Lance;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface LanceRepository extends JpaRepository<Lance, Long> {
    List<Lance> findByProdutoId(Long produtoId);
}

