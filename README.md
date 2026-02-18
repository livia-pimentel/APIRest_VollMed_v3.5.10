# Vollmed API 🩺 - Testes & Documentação

Este repositório contém uma versão específica da aplicação **Vollmed**, desenvolvida durante os cursos de Spring Boot da Alura. O foco principal aqui é a implementação de testes, documentação automática e fluxos de build.

> **Nota:** As funcionalidades completas de negócio usando Spring Boot 4.0.2 encontram-se no meu repositório principal: [APIRest-VollMed](https://github.com/livia-pimentel/APIRest-VollMed)

---

## 🎯 Objetivos deste Projeto

* **Testes Automatizados:** Cobertura de testes de integração para Controllers utilizando `@SpringBootTest` e `MockMvc`.
* **Bean Overriding:** Uso do novo padrão `@MockitoBean` (Spring Boot 3.4+) para isolamento de dependências nos testes.
* **Documentação com SpringDoc:** Interface interativa via Swagger UI para exploração e teste dos endpoints.
* **Build & Package:** Fluxo de empacotamento `.jar` com gerenciamento de variáveis de ambiente.
* **Segurança:** Autenticação JWT integrada ao contexto de testes com `@WithMockUser`.

## 🛠️ Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3.5.10**
* **MySQL** & **Flyway**
* **JUnit 5**, **Mockito** & **AssertJ**
* **Maven**

---

## 🏗️ Configuração e Build

Para garantir que o projeto compile e os testes de integração passem, é necessário configurar as seguintes variáveis de ambiente:

```properties
DB_HOST_TEST=localhost:3306
DB_USER=seu_usuario
DB_PASSWORD=sua_senha
DB_TEST_NAME=vollmed_api_test
JWT_SECRET=seu_segredo_token
