package com.muniz.bff_java_spring.client;

import com.muniz.bff_java_spring.soap.model.ConsultaAntecedentesRequest;
import com.muniz.bff_java_spring.soap.model.ConsultaAntecedentesResponse;
import org.springframework.ws.client.core.WebServiceTemplate;

public class AntecedentesSoapClient {

    private final WebServiceTemplate webServiceTemplate;

    public AntecedentesSoapClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public ConsultaAntecedentesResponse buscarAntecedentes(String cpf) {
        // TODO 1: monte o objeto de request (ConsultaAntecedentesRequest) com o cpf
        ConsultaAntecedentesRequest request = new ConsultaAntecedentesRequest(cpf);

        // TODO 2: chame webServiceTemplate.marshalSendAndReceive(request) e faça o cast para ConsultaAntecedentesResponse
        ConsultaAntecedentesResponse response = (ConsultaAntecedentesResponse) webServiceTemplate.marshalSendAndReceive(request);

        // TODO 3: retorne o resultado
        return response;
    }
}