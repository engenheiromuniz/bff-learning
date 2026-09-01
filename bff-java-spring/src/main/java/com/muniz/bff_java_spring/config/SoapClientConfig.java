package com.muniz.bff_java_spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.muniz.bff_java_spring.soap.model.ConsultaAntecedentesRequest;
import com.muniz.bff_java_spring.soap.model.ConsultaAntecedentesResponse;

@Configuration
public class SoapClientConfig {

//    @Bean
//    public Jaxb2Marshaller marshaller() {
//        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//        marshaller.setContextPath("com.muniz.bff_java_spring.soap.model");
//        return marshaller;
//    }
    
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // Em vez de setContextPath, informamos as classes diretamente:
        marshaller.setClassesToBeBound(
            ConsultaAntecedentesRequest.class,
            ConsultaAntecedentesResponse.class
        );
        return marshaller;
    }    

    @Bean
    public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller) {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setMarshaller(marshaller);
        webServiceTemplate.setUnmarshaller(marshaller);
        webServiceTemplate.setDefaultUri("http://localhost:8081/ws");
        return webServiceTemplate;
    }
}