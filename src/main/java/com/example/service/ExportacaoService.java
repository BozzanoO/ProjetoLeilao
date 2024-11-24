package com.example.service;

import com.example.entity.Leilao;
import io.micronaut.serde.ObjectMapper;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Getter
@Setter
@AllArgsConstructor
@Singleton
public class ExportacaoService {

    @Inject
    private final ObjectMapper objectMapper;

    public void exportarLeilaoParaArquivo(Leilao leilao, String caminhoArquivo) throws IOException {
    File arquivo = new File(caminhoArquivo + ".DET");
    try (FileOutputStream fileOutputStream = new FileOutputStream(arquivo)) {
        objectMapper.writeValue(fileOutputStream, leilao);
    }
}
}
