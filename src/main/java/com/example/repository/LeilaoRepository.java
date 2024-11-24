package com.example.repository;

import com.example.entity.Leilao;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface LeilaoRepository extends JpaRepository<Leilao, Long> {
}
