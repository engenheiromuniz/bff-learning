package com.muniz.bff_java_spring.client;

import java.util.Optional;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import com.muniz.bff_java_spring.dto.CidadaoConsolidadoDto;

public class CadastroRestClient {

    private final RestClient restClient;

    public CadastroRestClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public Optional<CidadaoConsolidadoDto> buscarCadastro(String cpf) {
        try {
            CidadaoConsolidadoDto cidadao = restClient.get()
                    .uri("/rest/cadastro/{cpf}", cpf)
                    .retrieve()
                    .body(CidadaoConsolidadoDto.class);
            return Optional.ofNullable(cidadao);
        } catch (HttpClientErrorException.NotFound e) {
            return Optional.empty();
        }
    }
}