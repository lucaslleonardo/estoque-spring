# Sistema de Gerenciamento de Estoque

API REST desenvolvida com Java e Spring Boot para gerenciamento de produtos, categorias, usuários e movimentação de estoque. O projeto foi criado com o objetivo de praticar conceitos de desenvolvimento backend, arquitetura em camadas, autenticação com Spring Security e testes unitários utilizando JUnit 5 e Mockito. Durante o desenvolvimento, ferramentas de Inteligência Artificial Generativa foram utilizadas como apoio para esclarecimento de conceitos, revisão de código, discussão de boas práticas e auxílio na resolução de dúvidas.
---

##  Funcionalidades

- Cadastro de produtos
- Atualização de produtos
- Listagem de produtos
- Busca de produto por ID
- Exclusão de produtos

- Cadastro de categorias
- Atualização de categorias
- Listagem de categorias
- Exclusão de categorias

- Cadastro de usuários
- Alteração de usuários
- Exclusão de usuários

- Entrada de estoque
- Saída de estoque
- Validação de estoque insuficiente

- Autenticação utilizando Spring Security
- Criptografia de senhas com PasswordEncoder

---

## 🛠️ Tecnologias utilizadas

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- Hibernate
- PostgreSQL
- Maven
- Lombok
- JUnit 5
- Mockito

---

## Estrutura do projeto

```
src
 ├── controller
 ├── dto
 ├── entity
 ├── exception
 ├── repository
 ├── security
 ├── service
 └── roles
```

O projeto segue uma arquitetura em camadas, separando as responsabilidades entre Controller, Service, Repository e Entity.

---


##  Regras de negócio

### Produtos

- Não permite cadastrar produtos com nomes duplicados.
- O produto deve pertencer a uma categoria existente.

### Categorias

- Não permite categorias com nomes repetidos.

### Usuários

- Não permite cadastro de usuários com e-mail já existente.
- As senhas são armazenadas utilizando criptografia.

### Estoque

- Permite entrada de produtos no estoque.
- Permite saída apenas quando houver quantidade suficiente.
- Impede movimentações de produtos inexistentes.

---

## Objetivo do projeto

Este projeto foi desenvolvido com fins de estudo para praticar:

- Desenvolvimento de APIs REST
- Arquitetura em camadas
- Spring Boot
- Spring Data JPA
- Spring Security
- Tratamento de exceções
- Testes unitários com JUnit e Mockito

---

## Autor

Lucas Leonardo

