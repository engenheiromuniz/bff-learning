package com.muniz.bff_java_spring.service;



import com.muniz.bff_java_spring.client.AntecedentesSoapClient;
import com.muniz.bff_java_spring.client.CadastroRestClient;
import com.muniz.bff_java_spring.dto.CidadaoConsolidadoDto;
import com.muniz.bff_java_spring.soap.model.ConsultaAntecedentesRequest;
import com.muniz.bff_java_spring.soap.model.ConsultaAntecedentesResponse;
import org.junit.jupiter.api.Test;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.client.RestClient;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.*;

class CidadaoServiceTest {

    @Test
    void deveConsolidarCadastroEAntecedentes() {
        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
        CadastroRestClient cadastroRestClient = new CadastroRestClient(restClient);

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(
                ConsultaAntecedentesRequest.class,
                ConsultaAntecedentesResponse.class
        );
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate(marshaller);
        webServiceTemplate.setDefaultUri("http://localhost:8081/ws");
        AntecedentesSoapClient antecedentesSoapClient = new AntecedentesSoapClient(webServiceTemplate);

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        CidadaoService service = new CidadaoService(cadastroRestClient, antecedentesSoapClient, executor);

        Optional<CidadaoConsolidadoDto> resultado = service.buscarCidadaoConsolidado("11111111111");

        assertTrue(resultado.isPresent(), "O cidadão deveria existir no cadastro");
        assertEquals("Joao da Silva", resultado.get().getNome());
        assertTrue(resultado.get().getPossuiRestricao(), "O CPF 11111111111 deve possuir restrição");
    }

    @Test
    void deveRetornarVazioQuandoCidadaoNaoExisteNoCadastro() {
        RestClient restClient = RestClient.builder()
                .baseUrl("http://localhost:8081")
                .build();
        CadastroRestClient cadastroRestClient = new CadastroRestClient(restClient);

        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(
                ConsultaAntecedentesRequest.class,
                ConsultaAntecedentesResponse.class
        );
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate(marshaller);
        webServiceTemplate.setDefaultUri("http://localhost:8081/ws");
        AntecedentesSoapClient antecedentesSoapClient = new AntecedentesSoapClient(webServiceTemplate);

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        CidadaoService service = new CidadaoService(cadastroRestClient, antecedentesSoapClient, executor);

        Optional<CidadaoConsolidadoDto> resultado = service.buscarCidadaoConsolidado("99999999999");

        assertTrue(resultado.isEmpty(), "O resultado deveria ser vazio para um CPF inexistente");
    }
}