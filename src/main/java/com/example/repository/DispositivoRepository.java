package com.example.repository;

import com.example.entity.Dispositivo;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {
}
