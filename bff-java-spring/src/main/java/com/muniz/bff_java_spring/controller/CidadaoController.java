package com.muniz.bff_java_spring.controller;

import com.muniz.bff_java_spring.dto.CidadaoConsolidadoDto;
import com.muniz.bff_java_spring.service.CidadaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cidadao")
public class CidadaoController {

    private final CidadaoService cidadaoService;

    public CidadaoController(CidadaoService cidadaoService) {
        this.cidadaoService = cidadaoService;
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<CidadaoConsolidadoDto> buscar(@PathVariable String cpf) {

        // Validação simples de entrada: evita mandar lixo para os sistemas legados
        if (cpf == null || !cpf.matches("\\d{11}")) {
            return ResponseEntity.badRequest().build();
        }

        Optional<CidadaoConsolidadoDto> resultado = cidadaoService.buscarCidadaoConsolidado(cpf);

        return resultado
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}