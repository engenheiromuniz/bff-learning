package com.muniz.bff_java_spring.client;

import com.muniz.bff_java_spring.soap.model.ConsultaAntecedentesRequest;
import com.muniz.bff_java_spring.soap.model.ConsultaAntecedentesResponse;
import org.junit.jupiter.api.Test;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

import static org.junit.jupiter.api.Assertions.*;

class AntecedentesSoapClientTest {

    @Test
    void deveConsultarAntecedentesComRestricao() {
        // 1. Configura o marshaller manualmente para as classes SOAP
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(
            ConsultaAntecedentesRequest.class,
            ConsultaAntecedentesResponse.class
        );

        // 2. Configura o WebServiceTemplate apontando para o mock SOAP na porta 8081/ws
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate(marshaller);
        webServiceTemplate.setDefaultUri("http://localhost:8081/ws");

        // 3. Instancia o client alvo do teste
        AntecedentesSoapClient client = new AntecedentesSoapClient(webServiceTemplate);

        // 4. Executa a chamada real para o serviço legado SOAP
        ConsultaAntecedentesResponse resposta = client.buscarAntecedentes("11111111111");

        // 5. Valida se a resposta veio preenchida e contém os dados esperados do mock
        assertNotNull(resposta);
        assertEquals("11111111111", resposta.getCpf());
        assertTrue(resposta.isPossuiRestricao());
    }
}