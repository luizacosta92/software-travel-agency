# API de Destinos de Viagem

Projeto desenvolvido em Java 21 com Spring Boot para gerenciamento básico de destinos turísticos em memória, organizado em camadas e pronto para evolução futura com banco de dados e segurança.

## Visão geral

A aplicação expõe uma API REST para cadastrar, listar, pesquisar, detalhar, atualizar, avaliar e excluir destinos de viagem. Nesta primeira versão, os dados ficam armazenados em memória, sem banco de dados real.

## Arquitetura proposta

O projeto segue uma arquitetura em camadas:

- `controller`: recebe as requisições HTTP e devolve as respostas.
- `service`: concentra as regras de negócio.
- `repository`: isola o armazenamento em memória.
- `model`: representa os dados do domínio.
- `dto`: define os dados de entrada e saída da API.
- `exception`: centraliza o tratamento de erros.

Essa estrutura facilita a manutenção e permite trocar o armazenamento em memória por JPA no futuro sem mudar o controller.

## Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Web
- Spring Validation
- JUnit 5

## Endpoints principais

- `POST /destinos` - cadastra um destino.
- `GET /destinos` - lista todos os destinos.
- `GET /destinos/pesquisa?nome=...&local=...` - pesquisa por nome ou localização.
- `GET /destinos/{id}` - detalha um destino específico.
- `PUT /destinos/{id}` - atualiza um destino.
- `PATCH /destinos/{id}/avaliacoes` - registra uma avaliação e recalcula a média.
- `DELETE /destinos/{id}` - exclui um destino.

## Estrutura principal

Os arquivos principais estão em `demo/src/main/java/com/agenciaviagens/api/`.

## Como executar

1. Abra a pasta `demo` no terminal.
2. Execute `mvn spring-boot:run`.
3. Acesse `http://localhost:8080`.

## Como testar

Execute `mvn test` para rodar os testes automatizados.

## Observação

O armazenamento é temporário e os dados são perdidos ao reiniciar a aplicação.
