package com.ex.mock.soap.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ConsultaAntecedentesRequest", namespace = "http://ex.com/mock/antecedentes")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConsultaAntecedentesRequest {

    @XmlElement(namespace = "http://ex.com/mock/antecedentes", required = true)
    private String cpf;

    public ConsultaAntecedentesRequest() {
    }

    public ConsultaAntecedentesRequest(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
