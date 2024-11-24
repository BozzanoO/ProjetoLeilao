package com.example.entity;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Serdeable
public class Dispositivo extends Produto {
    private String especificacoesTecnicas;
    private String tipo;
}
