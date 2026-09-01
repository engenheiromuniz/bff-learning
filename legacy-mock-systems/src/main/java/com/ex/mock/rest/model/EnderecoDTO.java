package com.ex.mock.rest.model;

public record EnderecoDTO(
        String logradouro,
        String numero,
        String bairro,
        String cidade,
        String uf,
        String cep
) {
}
