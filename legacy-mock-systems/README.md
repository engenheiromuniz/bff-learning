# legacy-mock-systems

Aplicação auxiliar, **não faz parte do BFF nem do portfólio final**. Simula, numa única
porta (8081), os dois sistemas legados que o BFF vai consumir no exercício
`bff-mulesoft-learning`:

| Sistema | Protocolo | Endpoint |
|---|---|---|
| Antecedentes | SOAP | `POST http://localhost:8081/ws` (WSDL em `/ws/antecedentes.wsdl`) |
| Cadastro | REST | `GET http://localhost:8081/rest/cadastro/{cpf}` |

Ambos têm latência artificial (`Thread.sleep`) para simular o comportamento real
de sistemas legados — isso importa quando você for medir o ganho de chamar os
dois em paralelo no BFF.

## Como rodar

```bash
cd legacy-mock-systems
mvn spring-boot:run
```

## CPFs de teste

| CPF | Cadastro (REST) | Antecedentes (SOAP) |
|---|---|---|
| `11111111111` | João da Silva – Brasília/DF | possui restrição |
| `22222222222` | Maria Oliveira – São Paulo/SP | possui restrição |
| `33333333333` | Carlos Pereira – Curitiba/PR | sem restrição |
| qualquer outro | 404 Not Found | sem restrição (retorna `false`, o serviço nunca "quebra") |

## Testando manualmente

**REST:**
```bash
curl http://localhost:8081/rest/cadastro/11111111111
```

**SOAP** (envelope mínimo):
```bash
curl -X POST http://localhost:8081/ws \
  -H "Content-Type: text/xml" \
  -d '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ant="http://ex.com/mock/antecedentes">
        <soapenv:Body>
          <ant:ConsultaAntecedentesRequest>
            <ant:cpf>11111111111</ant:cpf>
          </ant:ConsultaAntecedentesRequest>
        </soapenv:Body>
      </soapenv:Envelope>'
```

Ou, mais fácil: importe o WSDL (`http://localhost:8081/ws/antecedentes.wsdl`) no
SoapUI / Postman / Insomnia e gere a requisição automaticamente.
