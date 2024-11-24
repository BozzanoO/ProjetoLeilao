package com.example;

import java.io.IOException;

import com.example.entity.Leilao;
import com.example.service.ExportacaoService;

import io.micronaut.runtime.Micronaut;
import io.micronaut.context.annotation.Context;
import io.micronaut.serde.ObjectMapper;
import jakarta.inject.Inject;
import lombok.AllArgsConstructor;

@Context
@AllArgsConstructor
public class ExportacaoMain {
    
    @Inject
    private final ObjectMapper objectMapper;
    @Inject
    private final ExportacaoService exportacaoService;


    public void executarExportacao() {
        Leilao leilao = new Leilao();
        leilao.setNome("Leilão de Exemplo");

        try {
            exportacaoService.exportarLeilaoParaArquivo(leilao, "caminho/para/arquivo");
            System.out.println("Arquivo exportado com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao exportar o arquivo: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        var applicationContext = Micronaut.run(ExportacaoMain.class);
        
        ExportacaoMain exportacaoMain = applicationContext.getBean(ExportacaoMain.class);
        exportacaoMain.executarExportacao();
    }
}
