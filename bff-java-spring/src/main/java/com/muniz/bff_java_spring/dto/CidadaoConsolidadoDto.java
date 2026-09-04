package com.muniz.bff_java_spring.dto;



public class CidadaoConsolidadoDto {
	
	
	
    private String cpf;
    private String nome;
    private String dataNascimento;
    private String sexo;
    private EnderecoDto endereco;
    
    private Boolean possuiRestricao;
    private String tipoRestricao;
    
    
	public CidadaoConsolidadoDto(String cpf, String nome, String dataNascimento, String sexo, EnderecoDto endereco) {
		this.cpf = cpf;
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.endereco = endereco;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getDataNascimento() {
		return dataNascimento;
	}


	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}


	public String getSexo() {
		return sexo;
	}


	public void setSexo(String sexo) {
		this.sexo = sexo;
	}


	public EnderecoDto getEndereco() {
		return endereco;
	}


	public void setEndereco(EnderecoDto endereco) {
		this.endereco = endereco;
	}
	
	public Boolean getPossuiRestricao() {
	    return possuiRestricao;
	}

	public void setPossuiRestricao(Boolean possuiRestricao) {
	    this.possuiRestricao = possuiRestricao;
	}

	public String getTipoRestricao() {
	    return tipoRestricao;
	}

	public void setTipoRestricao(String tipoRestricao) {
	    this.tipoRestricao = tipoRestricao;
	}	
}
