package com.example.repository;

import com.example.entity.EntidadeFinanceira;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface EntidadeFinanceiraRepository extends JpaRepository<EntidadeFinanceira, Long> {
}
