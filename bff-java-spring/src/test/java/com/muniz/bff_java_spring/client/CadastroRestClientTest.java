package com.muniz.bff_java_spring.client;

import com.muniz.bff_java_spring.dto.CidadaoConsolidadoDto;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CadastroRestClientTest {

    @Test
    void deveBuscarCadastroExistente() {
        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
        CadastroRestClient client = new CadastroRestClient(restClient);

        Optional<CidadaoConsolidadoDto> resultado = client.buscarCadastro("11111111111");

        // TODO 1 resolvido: valida se o Optional veio preenchido
        assertTrue(resultado.isPresent());
        
        // TODO 2 resolvido: extrai o objeto de dentro do Optional e valida o nome esperado
        CidadaoConsolidadoDto cidadao = resultado.get();
        assertEquals("Joao da Silva", cidadao.getNome());
    }

    @Test
    void deveRetornarVazioQuandoCpfNaoExiste() {
        // TODO 3 resolvido: testa o cenário de CPF inexistente esperando um Optional vazio (404 tratado)
        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
        CadastroRestClient client = new CadastroRestClient(restClient);

        Optional<CidadaoConsolidadoDto> resultado = client.buscarCadastro("99999999999");

        assertFalse(resultado.isPresent());
    }
}