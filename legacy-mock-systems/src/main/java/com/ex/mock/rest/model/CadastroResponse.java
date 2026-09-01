package com.ex.mock.rest.model;

public record CadastroResponse(
        String cpf,
        String nome,
        String dataNascimento,
        String sexo,
        EnderecoDTO endereco
) {
}
