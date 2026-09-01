package com.ex.mock.soap.model;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ConsultaAntecedentesResponse", namespace = "http://ex.com/mock/antecedentes")
@XmlAccessorType(XmlAccessType.FIELD)
public class ConsultaAntecedentesResponse {

    @XmlElement(namespace = "http://ex.com/mock/antecedentes", required = true)
    private String cpf;

    @XmlElement(namespace = "http://ex.com/mock/antecedentes", required = true)
    private boolean possuiRestricao;

    @XmlElement(namespace = "http://ex.com/mock/antecedentes")
    private String tipoRestricao;

    @XmlElement(namespace = "http://ex.com/mock/antecedentes", required = true)
    private String dataConsulta;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isPossuiRestricao() {
        return possuiRestricao;
    }

    public void setPossuiRestricao(boolean possuiRestricao) {
        this.possuiRestricao = possuiRestricao;
    }

    public String getTipoRestricao() {
        return tipoRestricao;
    }

    public void setTipoRestricao(String tipoRestricao) {
        this.tipoRestricao = tipoRestricao;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }
}
