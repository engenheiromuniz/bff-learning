package com.ex.mock.data;

import com.ex.mock.rest.model.CadastroResponse;
import com.ex.mock.rest.model.EnderecoDTO;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MockCitizenRepository {

    private final Map<String, CadastroResponse> base = new ConcurrentHashMap<>();

    public MockCitizenRepository() {
        base.put("11111111111", new CadastroResponse(
                "11111111111", "Joao da Silva", "1985-04-12", "M",
                new EnderecoDTO("Rua das Flores", "123", "Centro", "Brasilia", "DF", "70000-000")));

        base.put("22222222222", new CadastroResponse(
                "22222222222", "Maria Oliveira", "1990-09-30", "F",
                new EnderecoDTO("Av. Paulista", "1000", "Bela Vista", "Sao Paulo", "SP", "01310-100")));

        base.put("33333333333", new CadastroResponse(
                "33333333333", "Carlos Pereira", "1978-01-05", "M",
                new EnderecoDTO("Rua XV de Novembro", "45", "Centro", "Curitiba", "PR", "80020-310")));
    }

    public CadastroResponse buscarPorCpf(String cpf) {
        return base.get(cpf);
    }
}
