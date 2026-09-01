package com.ex.mock.rest.controller;

import com.ex.mock.data.MockCitizenRepository;
import com.ex.mock.rest.model.CadastroResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Simula o sistema REST moderno de Cadastro. Tambem com latencia artificial
 * (150-550ms) para deixar realista a comparacao de performance com o SOAP.
 */
@RestController
@RequestMapping("/rest/cadastro")
public class CadastroController {

    private final MockCitizenRepository repository;

    public CadastroController(MockCitizenRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<CadastroResponse> buscar(@PathVariable String cpf) {
        simularLatenciaLegado();

        CadastroResponse cadastro = repository.buscarPorCpf(cpf);
        if (cadastro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cadastro);
    }

    private void simularLatenciaLegado() {
        try {
            Thread.sleep(150 + (long) (Math.random() * 400));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
