# Vollmed API - Testes & Documentação

Este repositório contém uma versão específica da aplicação **Vollmed**, desenvolvida durante os cursos de Spring Boot da Alura. O foco desta versão foi a implementação de testes automatizados, documentação interativa e preparação para deploy.

> **Nota:** As funcionalidades completas de negócio e a versão mais recente (Spring Boot 4.0.2) encontram-se no meu repositório APIRest-VollMed https://github.com/livia-pimentel/APIRest-VollMed

## 🎯 Objetivos deste Projeto

* **Testes Automatizados:** Implementação de testes de unidade e integração utilizando `@SpringBootTest`, `MockMvc` e o novo padrão `@MockitoBean`.
* **Documentação com SpringDoc:** Configuração do Swagger UI para documentação automática dos endpoints.
* **Build & Package:** Preparação do artefato `.jar` via Maven, simulando o fluxo de envio para produção.
* **Segurança:** Configuração de autenticação JWT e isolamento de credenciais sensíveis via variáveis de ambiente.

## 🛠️ Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3.5.10** (Versão ajustada para compatibilidade com SpringDoc)
* **Spring Data JPA** & **Flyway** (Migrações de banco de dados)
* **MySQL** (Banco de dados relacional)
* **Spring Security** (Autenticação JWT)
* **JUnit 5** & **Mockito**
* **Maven** (Gerenciamento de dependências e build)
