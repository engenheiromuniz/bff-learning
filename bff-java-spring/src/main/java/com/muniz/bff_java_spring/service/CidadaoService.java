package com.muniz.bff_java_spring.service;

import com.muniz.bff_java_spring.client.AntecedentesSoapClient;
import com.muniz.bff_java_spring.client.CadastroRestClient;
import com.muniz.bff_java_spring.dto.CidadaoConsolidadoDto;
import com.muniz.bff_java_spring.soap.model.ConsultaAntecedentesResponse;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import org.springframework.stereotype.Service;

@Service
public class CidadaoService {

    private final CadastroRestClient     cadastroRestClient;
    private final AntecedentesSoapClient antecedentesSoapClient;
    private final ExecutorService        executor;

    public CidadaoService(CadastroRestClient cadastroRestClient,
                           AntecedentesSoapClient antecedentesSoapClient,
                           ExecutorService executor) {
        this.cadastroRestClient = cadastroRestClient;
        this.antecedentesSoapClient = antecedentesSoapClient;
        this.executor = executor;
    }

    public Optional<CidadaoConsolidadoDto> buscarCidadaoConsolidado(String cpf) {
        // TODO 1: dispare a busca do cadastro (CadastroRestClient) num CompletableFuture, usando o executor
        CompletableFuture<Optional<CidadaoConsolidadoDto>> futuroCadastro = 
                CompletableFuture.supplyAsync(() -> cadastroRestClient.buscarCadastro(cpf), executor);
        
        // TODO 2: dispare a busca dos antecedentes (AntecedentesSoapClient) em OUTRO CompletableFuture, usando o executor
        CompletableFuture<ConsultaAntecedentesResponse> futuroAntecedentes = 
                CompletableFuture.supplyAsync(() -> antecedentesSoapClient.buscarAntecedentes(cpf), executor);                
                
        // TODO 3: dê .join() nos dois (o cadastro vem como Optional<CidadaoConsolidadoDto>, os antecedentes como ConsultaAntecedentesResponse)
        Optional<CidadaoConsolidadoDto> cadastroOpt = futuroCadastro.join();
        ConsultaAntecedentesResponse antecedentes = futuroAntecedentes.join();
        
        // TODO 4: pense - se o cadastro (Optional) vier vazio, o cidadão existe? o que o método deveria devolver?
        if (cadastroOpt.isEmpty()) {
            return Optional.empty();
        }
        
        // TODO 5: se o cadastro existir, preencha nele os campos possuiRestricao/tipoRestricao vindos da resposta SOAP, e devolva Optional.of(...)
        CidadaoConsolidadoDto cidadao = cadastroOpt.get();
        
        if (antecedentes != null) {
            cidadao.setPossuiRestricao(antecedentes.isPossuiRestricao());
            cidadao.setTipoRestricao(antecedentes.getTipoRestricao());
        }

        return Optional.of(cidadao);
    }
}