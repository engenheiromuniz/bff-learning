package com.ex.mock.soap.endpoint;

import com.ex.mock.soap.model.ConsultaAntecedentesRequest;
import com.ex.mock.soap.model.ConsultaAntecedentesResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

/**
 * Simula o legado SOAP de Antecedentes. Propositalmente lento (200-700ms)
 * para reproduzir a latencia tipica de um sistema antigo de governo,
 * o que sera relevante quando voce tratar concorrencia no BFF com Virtual Threads.
 */
@Endpoint
public class AntecedentesEndpoint {

    private static final String NAMESPACE_URI = "http://ex.com/mock/antecedentes";

    // CPFs de teste que retornam restricao = true
    private static final Set<String> CPFS_COM_RESTRICAO = Set.of("11111111111", "22222222222");

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ConsultaAntecedentesRequest")
    @ResponsePayload
    public ConsultaAntecedentesResponse consultarAntecedentes(@RequestPayload ConsultaAntecedentesRequest request) {

        simularLatenciaLegado();

        ConsultaAntecedentesResponse response = new ConsultaAntecedentesResponse();
        response.setCpf(request.getCpf());
        response.setDataConsulta(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        boolean possuiRestricao = CPFS_COM_RESTRICAO.contains(request.getCpf());
        response.setPossuiRestricao(possuiRestricao);
        response.setTipoRestricao(possuiRestricao ? "PENDENCIA_JUDICIAL" : null);

        return response;
    }

    private void simularLatenciaLegado() {
        try {
            Thread.sleep(200 + (long) (Math.random() * 500));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
